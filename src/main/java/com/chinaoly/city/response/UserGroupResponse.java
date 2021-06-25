package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UserGroupResponse implements Serializable {
    private Integer id;
    private String userGroupName;
    private String userGroupCode;
    private String tenantId;
    private Date gmtModified;
}
