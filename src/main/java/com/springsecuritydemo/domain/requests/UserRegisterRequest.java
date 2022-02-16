package com.springsecuritydemo.domain.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String confirm_password;
}
