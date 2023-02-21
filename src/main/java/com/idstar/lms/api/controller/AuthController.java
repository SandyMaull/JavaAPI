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
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final AuthenticationManager authenticationManager;



    @PostMapping("/add")
    @ApiOperation(value = "Create new User")
    public ResponseEntity<ResponseMapper> saveUser(@RequestBody @Validated Users users) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(new ResponseMapper(HttpStatus.CREATED.value(),
                "SUCCESS", userAssembler.toUserVO(this.userService.addUser(users))));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login to LMS")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Users users) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                    "SUCCESS", TokenUtils.getInstance()
                    .generateJwtToken(user.getUsername(),
                            user.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList())
                    )));

        }
        else{
            return ResponseEntity.ok(new ResponseMapper(HttpStatus.UNAUTHORIZED.value(),
                    "UNAUTHORIZED", "Unauthorized"));
        }
    }


    @GetMapping("/refresh")
    @ApiOperation("Refresh JWT Token")
    public Map<String, String> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return TokenUtils.getInstance().updateAccessToken(request.getHeader(AUTHORIZATION), userService);
    }


}