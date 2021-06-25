package com.chinaoly.city.controller;

import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chinaoly.city.request.DepartmentListRequest;
import com.chinaoly.city.request.DepartmentRequest;
import com.chinaoly.city.response.DepartmentResponse;
import com.chinaoly.city.response.UMSResponse;
import com.chinaoly.city.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "部门管理相关接口")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/department")
    @ApiOperation("新增部门")
    public ResponseEntity<Boolean> addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        UMSResponse response = departmentService.addDepartment(departmentRequest);
        return ResponseEntity.ok().body(Objects.nonNull(response) ? response.getSuccess() : Boolean.FALSE);
    }

    @GetMapping("/user/department")
    @ApiOperation("获取用户下所有部门")
    public ResponseEntity<DepartmentResponse> departments(@RequestParam("userAccount") String userAccount) {
        return ResponseEntity.ok().body(departmentService.getAllDepartmentsByUserAccount(userAccount));
    }

    @PostMapping("/departments")
    @ApiOperation("获取所有部门")
    public ResponseEntity<DepartmentResponse> departments(@RequestBody DepartmentListRequest departmentListRequest) {
        return ResponseEntity.ok().body(departmentService.getAllDepartments(departmentListRequest));
    }
}
