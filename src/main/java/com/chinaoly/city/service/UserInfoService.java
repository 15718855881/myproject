package com.chinaoly.city.service;

import java.net.URI;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.chinaoly.city.request.UserInfoRequest;
import com.chinaoly.city.response.UMSResponse;
import com.chinaoly.city.response.UserInfoResponse;
import com.chinaoly.city.response.UserOtherInfoResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RSAService rsaService;

    @Value("${ums.url}")
    private String usmUrl;

    @Value("${umscommon.requesttype.get}")
    private String requesttypeGet;

    @Value("${umscommon.requesttype.post}")
    private String requesttypePost;

    @Value("${umscommon.uri.login}")
    private String uriLogin;

    @Value("${umscommon.uri.user_other_info}")
    private String userOtherInfo;

    @Value("${umscommon.uri.adduser}")
    private String addUser;

    @Value("${umscommon.uri.lock_user}")
    private String lockUser;


    public void updateCustomerReference(String quoteId, Map<String, Object> updates) {
        RequestEntity request = RequestEntity
                .patch(URI.create("http://quotation/api/v1/quote/" + quoteId))
                .body(updates);
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<Object>() {
        };
        try {
            this.restTemplate.exchange(request, responseType);
        } catch (RestClientResponseException e) {
            log.error("update user info error, error message is {}", e.getMessage());
        }
    }

    public UMSResponse getUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("GET"));
        RequestEntity request = RequestEntity.get(UriComponentsBuilder
                .fromUriString(usmUrl + "/user/sdk/login")
                .queryParam("password", "A66ABB5684C45962D887564F08346E8D")
                .queryParam("userAccount", "testaccount")
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

    public UMSResponse getUserInfo(String userAccount, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders(requesttypeGet));
        RequestEntity request = RequestEntity.get(UriComponentsBuilder
                //.fromUriString(usmUrl + "/user/sdk/login")
                .fromUriString(usmUrl + uriLogin)
                .queryParam("password", password)
                .queryParam("userAccount", userAccount)
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


    public UserOtherInfoResponse getUserOtherInfo(String userAccount, String appCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders(requesttypeGet));
        headers.add("timestamp", new Date().toString());
        RequestEntity request = RequestEntity.get(UriComponentsBuilder
                //.fromUriString(usmUrl + "/user/sdk/getUserOtherInfo")
                .fromUriString(usmUrl + userOtherInfo)
                .queryParam("userAccount", userAccount)
                .queryParam("appCode", appCode)
                .build().toUri()).headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<UserOtherInfoResponse>() {
        };
        try {
            return (UserOtherInfoResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get user other info error, error message is {}", e.getMessage());
        }
        return null;
    }

    public UserInfoResponse addUser(UserInfoRequest userInfoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders(requesttypePost));
        RequestEntity request = RequestEntity.post(UriComponentsBuilder
                .fromUriString(usmUrl + addUser)
                .build().toUri()).headers(headers).body(userInfoRequest);
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<UserInfoResponse>() {
        };
        try {
            return (UserInfoResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get add user info error, error message is {}", e.getMessage());
        }
        return null;
    }

    public UserInfoResponse lock(String userAccount, Boolean isLock) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders(requesttypePost));
        RequestEntity request = RequestEntity.post(UriComponentsBuilder
                .fromUriString(usmUrl + lockUser)
                .queryParam("userAccount", userAccount)
                .queryParam("isLock", isLock)
                .build().toUri()).headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<UserInfoResponse>() {
        };
        try {
            return (UserInfoResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get lock info error, error message is {}", e.getMessage());
        }
        return null;
    }

}
