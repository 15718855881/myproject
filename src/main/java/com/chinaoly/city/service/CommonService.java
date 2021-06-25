package com.chinaoly.city.service;

import com.chinaoly.city.response.UMSResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class CommonService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RSAService rsaService;

    @Value("${ums.url}")

    private String usmUrl;

    public UMSResponse getUserInfo(Map<String,Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders((String)map.get("requesttype")));
        RequestEntity request = RequestEntity.get(UriComponentsBuilder
                .fromUriString(usmUrl + (String)map.get("URI"))//"/user/sdk/login"
                .queryParam("password", (String)map.get("password"))
                .queryParam("userAccount", (String)map.get("userAccount"))
                .build().toUri()).headers(headers).build();

        ParameterizedTypeReference responseType = new ParameterizedTypeReference<UMSResponse>() {
        };
        try {
            return (UMSResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get user info error, error message is {}", e.getMessage());
        }
        return null;
    }
}
