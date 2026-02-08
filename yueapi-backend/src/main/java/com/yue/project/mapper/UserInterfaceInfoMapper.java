package com.yue.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yue.apicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 张淇越
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
* @createDate 2026-02-02 13:16:26
* @Entity
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

    boolean updateLeftNumByIncrement(Long id, int leftNum, int increment);
}




