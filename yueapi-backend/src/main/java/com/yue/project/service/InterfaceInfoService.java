package com.yue.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.apicommon.model.entity.InterfaceInfo;

/**
* @author 张淇越
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2026-01-31 15:34:12
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {


    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
