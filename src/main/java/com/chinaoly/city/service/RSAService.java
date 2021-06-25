package com.chinaoly.city.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import com.alibaba.citybrain.ums.common.utils.DateUtils;
import com.alibaba.citybrain.ums.common.utils.RsaSignUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RSAService {

    @Value("${ums.key}")
    private String key;
    @Value("${ums.app.code}")
    private String appCode;

    MultiValueMap<String, String> generateHeaders(String httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("appCode", appCode);
        headers.add("timestamp", DateUtils.getFormatedDateTime(new Date()));
        JSONObject jsonParam = new JSONObject();
        try {
            headers.add("sign", RsaSignUtil.sign(jsonParam, key, httpMethod));
        } catch (Exception e) {
            log.error("generate headers error, error message is {}", e.getMessage());
        }
        return headers;
    }
}
