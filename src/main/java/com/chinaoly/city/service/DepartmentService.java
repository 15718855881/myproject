package com.chinaoly.city.service;

import java.net.URI;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.chinaoly.city.request.DepartmentListRequest;
import com.chinaoly.city.request.DepartmentRequest;
import com.chinaoly.city.response.DepartmentResponse;
import com.chinaoly.city.response.UMSResponse;
import com.chinaoly.city.response.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentService {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RSAService rsaService;

    @Value("${ums.url}")
    private String usmUrl;


    public UMSResponse addDepartment(DepartmentRequest departmentRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("POST"));
        RequestEntity request = RequestEntity
                .post(URI.create(usmUrl + "/ums_open_service/user/sdk/aclAddDepartment"))
                .headers(headers)
                .body(departmentRequest);
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<Object>() {
        };
        try {
            return (UMSResponse) restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("add department error, error message is {}", e.getMessage());
        }
        return null;
    }

    public DepartmentResponse getAllDepartmentsByUserAccount(String userAccount) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("GET"));
        RequestEntity request = RequestEntity.get(UriComponentsBuilder
                .fromUriString(usmUrl + "/user/sdk/getUserDepartmentsByUserAccount")
                .queryParam("userAccount", userAccount)
                .build().toUri()).headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<DepartmentResponse>() {
        };
        try {
            return (DepartmentResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get user info error, error message is {}", e.getMessage());
        }
        return null;
    }

    public DepartmentResponse getAllDepartments(DepartmentListRequest departmentListRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("POST"));
        RequestEntity request = RequestEntity
                .post(URI.create(usmUrl + "/department/sdk/listDepartments"))
                .headers(headers).body(departmentListRequest);
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<DepartmentResponse>() {
        };
        try {
            return (DepartmentResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get all departments error, error message is {}", e.getMessage());
        }
        return null;
    }

    public DepartmentResponse getChildDepartments(String departmentName) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("GET"));
        RequestEntity request = RequestEntity
                .get(UriComponentsBuilder
                        .fromUriString(usmUrl + "/user/sdk/getChildDepartments")
                        .queryParam("departmentName", departmentName).build().toUri())
                .headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<DepartmentResponse>() {
        };
        try {
            return (DepartmentResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get child departments error, error message is {}", e.getMessage());
        }
        return null;
    }

    public DepartmentResponse getDepartmentDetail(String departmentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("GET"));
        RequestEntity request = RequestEntity
                .get(UriComponentsBuilder
                        .fromUriString(usmUrl + "/user/sdk/aclDepartmentDetail")
                        .queryParam("departmentId", departmentId).build().toUri())
                .headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<DepartmentResponse>() {
        };
        try {
            return (DepartmentResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("get department detail error, error message is {}", e.getMessage());
        }
        return null;
    }

    public UserInfoResponse deleteDepartmentDetail(String departmentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(rsaService.generateHeaders("GET"));
        RequestEntity request = RequestEntity
                .get(UriComponentsBuilder
                        .fromUriString(usmUrl + "/user/sdk/aclDeleteDepartments")
                        .queryParam("departmentId", departmentId).build().toUri())
                .headers(headers).build();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<UserInfoResponse>() {
        };
        try {
            return (UserInfoResponse) this.restTemplate.exchange(request, responseType).getBody();
        } catch (RestClientResponseException e) {
            log.error("delete department error, error message is {}", e.getMessage());
        }
        return null;
    }
}
