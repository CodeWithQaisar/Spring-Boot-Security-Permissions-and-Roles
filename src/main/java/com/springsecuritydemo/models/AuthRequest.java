package com.springsecuritydemo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    public String username;
    public String password;
}
