package com.chinaoly.city.common;

import com.chinaoly.city.response.BaseResponse;
import com.chinaoly.city.response.UMSResponse;

import java.io.Serializable;

/**
 * @author zhangyahui
 * @date 2021/6/24
 */
public class Result<T> implements Serializable {



    public static BaseResponse wrapErrorfulResult(Integer code, String message, Boolean success ) {
        BaseResponse umsResponse =new BaseResponse();
        umsResponse.setErrorCode(code);
        umsResponse.setErrorMsg(message);
        umsResponse.setSuccess(success);
        return umsResponse;
    }


}
