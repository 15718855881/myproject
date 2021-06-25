package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class AuthorityResponse implements Serializable {
    private String authorityName;
    private String status;
    private Date expireDate;
    private String expireDateStr;
    private Boolean isAlways;
    private Integer appAuthorityId;
    private String authorityCode;
    private String authorityParams;
    private Integer authorityObjectId;
    private Integer authorityContentId;
    private String appName;
    private Integer appId;
    private String appCode;
    private String homePageUrl;
    private String description;
    private Date gmtCreate;
}
