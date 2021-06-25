package com.chinaoly.city.request;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UserInfoRequest implements Serializable {
    private String userAccount;
    private String userName;
    private String email;
    private String telephone;
    private String description;
    private String departmentIds;
    private String departmentCodes;
    private String appCode;
    private Date extData;
}
