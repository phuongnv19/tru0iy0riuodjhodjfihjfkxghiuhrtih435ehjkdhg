package com.toprate.erp.rest;

import com.toprate.erp.authen.TokenProvider;
import com.toprate.erp.base.dto.CustomUserDetails;
import com.toprate.erp.rest.VM.LoginRequest;
import com.toprate.erp.rest.VM.LoginResponse;
import com.toprate.erp.service.impl.UserDetailServiceImpl;
import com.toprate.erp.service.impl.UserServiceImpl;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController extends AbstractRestController {
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest,
                        loginRequest.getPassword()
                )
        );
        Assert.notNull(authentication, MessageUtils.getMessage("authentication.is-fail"));

        // Nếu thông tin hợp lệ Set thông tin authentication vào Security Context
        //SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        CustomUserDetails customUserDetails=(CustomUserDetails) authentication.getPrincipal();
        String jwt = customUserDetails.getJwt();
        userServiceImpl.resetLoginFailCount(customUserDetails.getUsername());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponse(jwt,customUserDetails.getUser()), httpHeaders, HttpStatus.OK);
    }
    @GetMapping("user/logOut")
    public ResponseEntity logOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
