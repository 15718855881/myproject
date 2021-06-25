package com.chinaoly.city.response;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataResponse implements Serializable {
    @JSONField(name = "userid")
    private String userId;
    private String userName;
    private String userAccount;
    private String sex;
    private String session;
    private String newToken;
    private String tenantId;
}
