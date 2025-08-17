package com.example.demo.form;

import lombok.Data;

@Data
public class RegisterForm {
    private String userName;
    private String password;
    private String repeatPassword;
}
