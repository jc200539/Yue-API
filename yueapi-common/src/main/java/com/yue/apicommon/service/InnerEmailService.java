package com.yue.apicommon.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface InnerEmailService {
    String sendEmail(String sendTo) throws MessagingException, UnsupportedEncodingException;
}