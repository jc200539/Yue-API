package com.yue.yueapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yue.yueapiclientsdk.Utils.SignUtils;
import com.yue.yueapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;


public class YueApiClient {


    private String accessKey;

    private String secretKey;

    private static final String GATEWAY_URL = "http://localhost:8090/";

    public YueApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result =  HttpUtil.get(GATEWAY_URL + "api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }

    private Map<String,String> getHeaderMap(String body){
       Map<String,String > headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
//        headerMap.put("secretKey", secretKey);s
        headerMap.put("nonce", RandomUtil.randomNumbers(5));
        // 防止中文乱码
        headerMap.put("body", body);
        headerMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        headerMap.put("sign",SignUtils.genSign(body,secretKey));
        return headerMap;
    }



    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result =  HttpUtil.post(GATEWAY_URL + "api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }

    public String getNameByPostWithJson(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_URL + "api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = response.body();
        System.out.println("response = " + result);
        System.out.println("status = " + response.getStatus());
        return result;

    }

}