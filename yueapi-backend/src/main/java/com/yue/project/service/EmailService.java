package com.yue.project.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    String sendEmail(String sendTo) throws MessagingException, UnsupportedEncodingException ;


}
