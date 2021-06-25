package com.chinaoly.city.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class BaseResponse implements Serializable {
    private Integer errorCode;
    private String errorName;
    private String errorMsg;
    private Boolean success;
    private Boolean wrapped;
}
