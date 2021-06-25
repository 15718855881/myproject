package com.chinaoly.city.request;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class DepartmentRequest implements Serializable {
    @JSONField(name = "departmenteCode")
    private String departmentCode;
    private String departmentName;
    private String tenantId;
    private Long parentDepartmentId;
}
