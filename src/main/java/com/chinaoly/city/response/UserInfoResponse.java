package com.chinaoly.city.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserInfoResponse extends BaseResponse implements Serializable {
    private Boolean data;
}
