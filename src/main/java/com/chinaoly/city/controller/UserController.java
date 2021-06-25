package com.chinaoly.city.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;

import com.chinaoly.city.common.DemoErrors;
import com.chinaoly.city.common.Result;
import com.chinaoly.city.common.ServiceErrors;
import com.chinaoly.city.response.*;
import com.chinaoly.city.service.CommonService;
import com.chinaoly.city.util.MD5Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chinaoly.city.request.UserInfoRequest;
import com.chinaoly.city.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "用户信息")
@RequestMapping("/smart_city")
@Slf4j
public class UserController {

    @Resource
    private UserInfoService userInfoService;




    @GetMapping("/login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户账号"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
//    public ResponseEntity<Boolean> userInfo(String userName, String password) {
//        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
//            return ResponseEntity.ok().body(Boolean.TRUE);
//        }
//        password = MD5Tools.convert2MD5(password);
//        return ResponseEntity.ok().body(Objects.nonNull(userInfoService.getUserInfo(userName, password.toUpperCase()).getData()));
//    }
    public BaseResponse userInfo(String userName, String password)  {
        UMSResponse umsResponse =new UMSResponse ();
        try{
            log.info("用户登录接口----userName----"+userName+"----password----"+password);
            password = MD5Tools.convert2MD5(password);
            umsResponse = userInfoService.getUserInfo(userName,password.toUpperCase());
            log.info("用户登录接口---登录成功");
            return umsResponse;
        }catch(Exception e){
            log.error("get user info error, error message is {}", e.getMessage());
            return Result.wrapErrorfulResult(DemoErrors.SYSTEM_ERROR.getCode(),
                    DemoErrors.SYSTEM_ERROR.getMessage(),false);
        }
    }

    @GetMapping("/user_other_info")
    @ApiOperation("获取用户其他信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号", required = true),
            @ApiImplicitParam(name = "appCode", value = "应用 code", required = true)
    })
    public BaseResponse userOtherInfo(String userAccount, String appCode) {
        //return ResponseEntity.ok().body(userInfoService.getUserOtherInfo(userAccount, appCode));
        UserOtherInfoResponse umsResponse =new UserOtherInfoResponse ();
        try{
            log.info("获取用户其他信息----userAccount----"+userAccount+"----appCode----"+appCode);
            umsResponse = userInfoService.getUserOtherInfo(userAccount,appCode);
            log.info("获取用户其他信息成功");
            return umsResponse;
        }catch(Exception e){
            log.error("获取用户其他信息 error message is {}", e.getMessage());
            return Result.wrapErrorfulResult(DemoErrors.SYSTEM_ERROR.getCode(),
                    DemoErrors.SYSTEM_ERROR.getMessage(),false);
        }

    }

    @PostMapping("/user")
    @ApiOperation("新增用户")
    public BaseResponse addUser(@RequestBody UserInfoRequest userInfoRequest) {
        //return ResponseEntity.ok().body(Boolean.TRUE.equals(userInfoService.addUser(userInfoRequest).getData()));
        UserInfoResponse umsResponse =new UserInfoResponse ();
        try{
            log.info("新增用户----userInfoRequest----"+userInfoRequest);
            umsResponse = userInfoService.addUser(userInfoRequest);
            log.info("新增用户成功");
            return umsResponse;
        }catch(Exception e){
            log.error("新增用户 error message is {}", e.getMessage());
            return Result.wrapErrorfulResult(DemoErrors.SYSTEM_ERROR.getCode(),
                    DemoErrors.SYSTEM_ERROR.getMessage(),false);
        }
    }


    @GetMapping("/lock_user")
    @ApiOperation("lock用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号", required = true),
            @ApiImplicitParam(name = "isLock", value = "是否上锁", defaultValue = "false")
    })
    public BaseResponse lock(String userAccount, Boolean isLock) {
       //return ResponseEntity.ok().body(Boolean.TRUE.equals(userInfoService.lock(userAccount, isLock).getData()));
        UserInfoResponse umsResponse =new UserInfoResponse ();
        try{
            log.info("lock用户----userAccount----"+userAccount+"----isLock----"+isLock);
            umsResponse = userInfoService.lock(userAccount, isLock);
            log.info("lock用户成功");
            return umsResponse;
        }catch(Exception e){
            log.error("lock用户 error message is {}", e.getMessage());
            return Result.wrapErrorfulResult(DemoErrors.SYSTEM_ERROR.getCode(),
                    DemoErrors.SYSTEM_ERROR.getMessage(),false);
        }
    }
}
