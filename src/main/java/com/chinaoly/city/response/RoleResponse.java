package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class RoleResponse implements Serializable {
    private String id;
    private Date gmtCreate;
    private Date gmtModified;
    private String roleName;
    private Integer appId;
    private String description;
    private String roleCode;
}
