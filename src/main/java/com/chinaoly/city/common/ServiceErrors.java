package com.chinaoly.city.common;
/**
 * @author zhnagyahui
 * @date 2021/6/24
 */
public interface ServiceErrors {
       /**
         * 获取错误码
         *
         * @return Integer
         */
        Integer getCode();

        /**
         * 获取错误信息
         *
         * @return String
         */
        String getMessage();

}
