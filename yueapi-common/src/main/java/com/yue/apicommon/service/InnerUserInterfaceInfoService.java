package com.yue.apicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.apicommon.model.entity.UserInterfaceInfo;

/**
* @author 张淇越
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2026-02-02 13:16:26
*/
public interface InnerUserInterfaceInfoService {

    boolean invokeCount(long interfaceInfoId, long userId);

    boolean hasRemainingInvokeCount(long interfaceInfoId, long userId);

    int getApiRemainingCalls(long interfaceInfoId, long userId);

    boolean updateLeftNum(long interfaceInfoId, long userId, int leftNum, int increment);

    UserInterfaceInfo getUserInterfaceInfo(long userId, long interfaceInfoId);
}
