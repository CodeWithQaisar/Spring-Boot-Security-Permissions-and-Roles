package com.springsecuritydemo.controllers;


import com.springsecuritydemo.application.utility.JwtOutils;
import com.springsecuritydemo.application.security.MyBCryptPasswordEncoder;
import com.springsecuritydemo.domain.ApiResponse;
import com.springsecuritydemo.domain.requests.AuthRequest;
import com.springsecuritydemo.domain.requests.UserRegisterRequest;
import com.springsecuritydemo.domain.response.AuthResponse;
import com.springsecuritydemo.data.entities.User;
import com.springsecuritydemo.data.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {

    private com.springsecuritydemo.data.persistance.UserRepository UserRepository;
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtOutils jwtOutils;

    public UserController(UserRepository UserRepository,
                          MyBCryptPasswordEncoder myBCryptPasswordEncoder) {
        this.UserRepository = UserRepository;
        this.myBCryptPasswordEncoder = myBCryptPasswordEncoder;
    }

    @PostMapping("sign-in")
    public AuthResponse signIn(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.username);
        if (user != null && myBCryptPasswordEncoder.matches(authRequest.password, user.getPassword())) {
            return new AuthResponse(jwtOutils.create(user));
        }
        return null;
    }

    @PostMapping("sign-up")
    public ApiResponse signUp(@RequestBody UserRegisterRequest request) {

        //Do Process for Register User.........
        return new ApiResponse(request);
    }

}
