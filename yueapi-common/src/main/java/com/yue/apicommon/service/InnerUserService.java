package com.yue.apicommon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.apicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {

    User getInvokeUser(String accessKey);

    User getUserByToken(String token);
}
