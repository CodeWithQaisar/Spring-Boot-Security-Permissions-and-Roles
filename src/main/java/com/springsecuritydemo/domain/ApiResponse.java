package com.springsecuritydemo.domain;

public class ApiResponse {

    private String response_code = "200";
    private String message = "Success!";
    private Object data;

    public ApiResponse(String response_code,String message, Object data){
        this.response_code = response_code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Object data){
        this.response_code = response_code;
        this.message = message;
        this.data = data;
    }

}
