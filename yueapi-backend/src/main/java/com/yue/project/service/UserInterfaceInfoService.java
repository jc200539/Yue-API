package com.yue.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.apicommon.model.entity.UserInterfaceInfo;

/**
* @author 张淇越
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2026-02-02 13:16:26
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    boolean isLeftCount(long userId, long interfaceInfoId);

    int getApiRemainingCalls(long interfaceInfoId, long userId);

    boolean updateLeftNum(long interfaceInfoId, long userId, int calls, int increment);

    UserInterfaceInfo getUserInterfaceInfo(long userId, long interfaceInfoId);

    boolean applyForApiCallIncrease(Long userId, Long interfaceInfoId, Integer invokeCount);

}
