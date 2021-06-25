package com.chinaoly.city.request;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class DepartmentListRequest implements Serializable {
    private String tenantId;
    private String keyword;
}
