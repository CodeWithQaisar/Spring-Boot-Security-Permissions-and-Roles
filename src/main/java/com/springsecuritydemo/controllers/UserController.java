package com.springsecuritydemo.controllers;

import com.springsecuritydemo.application.utility.JwtOutils;
import com.springsecuritydemo.application.security.MyBCryptPasswordEncoder;
import com.springsecuritydemo.domain.ApiResponse;
import com.springsecuritydemo.domain.requests.AuthRequest;
import com.springsecuritydemo.domain.requests.UserRegisterRequest;
import com.springsecuritydemo.data.entities.User;
import com.springsecuritydemo.data.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final MyBCryptPasswordEncoder myBCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtOutils jwtOutils;

    @PostMapping("sign-in")
    public ApiResponse signIn(@RequestBody AuthRequest authRequest) {

        User user = userRepository.findByUsername(authRequest.username);
        if (user != null && myBCryptPasswordEncoder.matches(authRequest.password, user.getPassword())) {
            return new ApiResponse(jwtOutils.create(user));
        }
        return new ApiResponse("200", "Something Wrong!","null");
    }

    @PostMapping("sign-up")
    public ApiResponse signUp(@RequestBody UserRegisterRequest request) {

        //Do Process for Register User.........
        return new ApiResponse(request);
    }

}
