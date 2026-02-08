package com.yue.project.service.impl.inner;

import com.yue.apicommon.service.InnerEmailService;
import com.yue.project.service.EmailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@DubboService
public class InnerEmailServiceImpl implements InnerEmailService {

    @Autowired
    private EmailService emailService;

    @Override
    public String sendEmail(String sendTo) throws MessagingException, UnsupportedEncodingException {
        return emailService.sendEmail(sendTo);
    }
}
