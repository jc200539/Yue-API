package com.yue.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yue.apicommon.model.entity.InterfaceInfo;
import com.yue.project.common.ErrorCode;
import com.yue.project.exception.BusinessException;
import com.yue.apicommon.model.entity.UserInterfaceInfo;
import com.yue.project.mapper.InterfaceInfoMapper;
import com.yue.project.mapper.UserInterfaceInfoMapper;
import com.yue.project.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author 张淇越
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
* @createDate 2026-02-02 13:16:26
*/
@Slf4j
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    private final UserInterfaceInfoMapper userInterfaceInfoMapper;

    private final InterfaceInfoMapper interfaceInfoMapper;

    @Autowired
    public UserInterfaceInfoServiceImpl(UserInterfaceInfoMapper userInterfaceInfoMapper, InterfaceInfoMapper interfaceInfoMapper) {
        this.userInterfaceInfoMapper = userInterfaceInfoMapper;
        this.interfaceInfoMapper = interfaceInfoMapper;
    }

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if(userInterfaceInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (add) {
            if (userInterfaceInfo.getInterface_info_id() <= 0 || userInterfaceInfo.getUser_id() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeft_num() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if(interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        updateWrapper.eq("interface_info_id", interfaceInfoId);
        updateWrapper.gt("left_num",0);
        updateWrapper.setSql("left_num = left_num - 1, total_num = total_num + 1");
        return this.update(updateWrapper);
    }

    @Override
    public boolean isLeftCount(long userId, long interfaceInfoId) {
        if(interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("interface_info_id", interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(queryWrapper);
        return userInterfaceInfo.getLeft_num() > 0;
    }

    @Override
    public int getApiRemainingCalls(long interfaceInfoId, long userId) {
        if(interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("interface_info_id", interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(queryWrapper);
        return userInterfaceInfo.getLeft_num();
    }

    @Override
    public boolean updateLeftNum(long interfaceInfoId, long userId, int leftNum, int increment) {
        log.info("updateLeftNum方法调用:interfaceInfoId={}, userId={}", interfaceInfoId, userId);
        if (interfaceInfoId == 0 || userId <= 0) {
            log.info("interfaceInfoId非法或者userId非法");
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        log.info("updateLeftNum方法调用...");
        UserInterfaceInfo userInterfaceInfo = getUserInterfaceInfo(userId, interfaceInfoId);
        log.info("userInterfaceInfo = {}", userInterfaceInfo);
        if (userInterfaceInfo == null) {
            // 创建对应关系
            log.info("找不到对应的信息，新建中...");
            UserInterfaceInfo newUserInterfaceInfo = new UserInterfaceInfo();
            newUserInterfaceInfo.setUser_id(userId);
            newUserInterfaceInfo.setInterface_info_id(interfaceInfoId);
            newUserInterfaceInfo.setLeft_num(increment);
            boolean save = save(newUserInterfaceInfo);
            if (!save) {
                log.debug("userInterfaceInfo保存失败");
                return false;
            }
        } else {
            // 直接增加接口调用次数
            boolean updateLeftNumByIncrement = userInterfaceInfoMapper.
                    updateLeftNumByIncrement(userInterfaceInfo.getId(), leftNum, increment);
            if (!updateLeftNumByIncrement) {
                log.debug("接口调用次数更新失败");
            }
            log.info("接口调用次数更新成功");
        }
        return true;
    }

    @Override
    public UserInterfaceInfo getUserInterfaceInfo(long userId, long interfaceInfoId) {
        QueryWrapper<UserInterfaceInfo> userInterfaceInfoQueryWrapper = new QueryWrapper<>();
        userInterfaceInfoQueryWrapper.eq("user_id", userId);
        userInterfaceInfoQueryWrapper.eq("interface_info_id", interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(userInterfaceInfoQueryWrapper);
        if (userInterfaceInfo == null) {
            log.info("查不到对应的接口信息");
        }
        return userInterfaceInfo;
    }

    @Override
    public boolean applyForApiCallIncrease(Long userId, Long interfaceInfoId, Integer invokeCount) {
        // 1. 获取接口信息
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(interfaceInfoId);
        if (Objects.isNull(interfaceInfo)) {
            // 如果接口信息不存在，返回false
            log.warn("InterfaceInfo not found, interfaceInfoId: {}", interfaceInfoId);
            return false;
        }

        log.info("InterfaceInfo found: {}", interfaceInfo);

        // 2. 查找用户接口信息
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("interface_info_id", interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);

        // 3. 如果用户接口信息不存在，创建新的记录
        if (userInterfaceInfo == null) {
            log.info("UserInterfaceInfo not found for userId: {}, interfaceInfoId: {}, creating new record.", userId, interfaceInfoId);

            userInterfaceInfo = new UserInterfaceInfo();
            userInterfaceInfo.setUser_id(userId);
            userInterfaceInfo.setInterface_info_id(interfaceInfoId);
            userInterfaceInfo.setLeft_num(invokeCount); // 初始化剩余次数
            int insertCount = userInterfaceInfoMapper.insert(userInterfaceInfo);

            if (insertCount > 0) {
                log.info("Successfully created UserInterfaceInfo for userId: {}, interfaceInfoId: {}", userId, interfaceInfoId);
                return true;
            } else {
                log.error("Failed to create UserInterfaceInfo for userId: {}, interfaceInfoId: {}", userId, interfaceInfoId);
                return false;
            }
        }

        // 4. 增加调用次数
        Integer oldLeftNum = userInterfaceInfo.getLeft_num();
        Integer newLeftNum = oldLeftNum + invokeCount;
        log.debug("Old leftNum: {}, New leftNum: {}", oldLeftNum, newLeftNum);

        if (oldLeftNum >= 100 || newLeftNum < 0) {
            // 如果原本已经有100次调用次数了或者增加后的调用次数小于0，可能是非法操作，返回false
            log.warn("Invalid operation, oldLeftNum: {}, newLeftNum: {}", oldLeftNum, newLeftNum);
            return false;
        }

        // 5. 更新用户接口信息
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("left_num", newLeftNum)  // 更新剩余次数
                .eq("user_id", userId)
                .eq("interface_info_id", interfaceInfoId);
        int updateCount = userInterfaceInfoMapper.update(null, updateWrapper);

        if (updateCount > 0) {
            log.info("Successfully updated leftNum for userId: {}, interfaceInfoId: {}", userId, interfaceInfoId);
        } else {
            log.error("Failed to update leftNum for userId: {}, interfaceInfoId: {}", userId, interfaceInfoId);
        }

        return updateCount > 0;  // 如果更新成功，返回true，否则返回false
    }
}




