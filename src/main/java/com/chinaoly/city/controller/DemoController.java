package com.chinaoly.city.controller;

import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chinaoly.city.service.UserInfoService;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class DemoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/test")
    public String getUser() {
        return Objects.nonNull(userInfoService.getUser().getData()) ? "success" : "";
    }
}
