package com.chinaoly.city.response;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UMSResponse extends BaseResponse implements Serializable {

    private Map<String,Object> data;


}
