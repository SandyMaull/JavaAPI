package com.idstar.lms.api.controller;

import com.idstar.lms.api.components.UserAssembler;
import com.idstar.lms.api.constants.ResponseMapper;
import com.idstar.lms.api.model.user.Users;
import com.idstar.lms.api.service.UserService;
import com.idstar.lms.api.utils.TokenUtils;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/all")
    public ResponseEntity<ResponseMapper> getUsers() {
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", userService.getAllUsers()
                .stream().map(userAssembler::toUserVO).collect(Collectors.toList())));
    }

    @GetMapping("/{username}/employees")
    @ApiOperation("Get user Employee Data")
    public ResponseEntity<ResponseMapper> getUserEmployees(@PathVariable("username") String username) {
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", userService.getEmployees(username)));
    }

    @PostMapping("/employees/add")
    @ApiOperation("Add employee data to current User")
    public ResponseEntity<ResponseMapper> addEmployeesToUserByUser(
            @RequestBody List<Long> employeesId, HttpServletRequest request)
    {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", userService.addEmployeesToUserByUser(employeesId, authorizationHeader)));
    }
}