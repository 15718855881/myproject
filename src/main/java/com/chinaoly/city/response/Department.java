package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Department implements Serializable {
    private Integer departmentId;
    private String departmentName;
    private String parentDepartmentName;
    private String parentDepartmentId;
    private String description;
    private String departmentCode;
    private Date gmtModified;
    private Integer id;
    private Date gmtCreate;
}
