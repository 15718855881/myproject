package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class UserOtherInfoResponse extends BaseResponse implements Serializable {

    private Map<String, List> data;
}
