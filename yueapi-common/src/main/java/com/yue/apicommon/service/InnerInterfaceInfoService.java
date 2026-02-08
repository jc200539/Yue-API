package com.yue.apicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.apicommon.model.entity.InterfaceInfo;

/**
* @author 张淇越
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2026-01-31 15:34:12
*/
public interface InnerInterfaceInfoService{

    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

    InterfaceInfo getInterfaceInfo(Long id);
}
