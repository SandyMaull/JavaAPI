package com.idstar.lms.api.controller;

import com.idstar.lms.api.constants.ResponseMapper;
import com.idstar.lms.api.model.user.RoleToUsernameForm;
import com.idstar.lms.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Api(value = "Endpoints to receive admin methods")
public class AdminController {

    private final UserService userService;

    @PostMapping("/employees/add")
    @ApiOperation(value = "Add employee data to User")
    public ResponseEntity<ResponseMapper> addEmployeesToUserByAdmin(
            @RequestBody List<Long> employeesId, @RequestParam String username)
    {
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", userService.addEmployeesToUserByAdmin(employeesId, username)));
    }

    @PostMapping("/role/update")
    @ApiOperation(value = "Update User Role", response = ResponseMapper.class)
    public ResponseEntity<ResponseMapper> updateUserRole(@RequestBody RoleToUsernameForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRole());
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", userService.getUser(form.getUsername())));
    }
}
