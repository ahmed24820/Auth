package com.example.Authentication.AuthenticationController;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;
}
