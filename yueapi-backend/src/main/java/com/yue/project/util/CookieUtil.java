package com.yue.project.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie工具类 - 封装Cookie的获取/设置等公共方法
 */
@Component // 可注入Spring，也可写成静态方法直接调用
public class CookieUtil {

    /**
     * 根据Cookie名称获取Cookie值
     * @param request 请求对象
     * @param cookieName Cookie名称
     * @return 匹配的Cookie值，无则返回null
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        // 1. 判空：请求中无Cookie直接返回null
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        // 2. 遍历匹配Cookie名称
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        // 3. 无匹配的Cookie，返回null
        return null;
    }
}