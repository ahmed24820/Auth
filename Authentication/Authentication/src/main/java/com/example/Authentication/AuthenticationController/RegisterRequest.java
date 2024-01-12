package com.example.Authentication.AuthenticationController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;

    private String lastname;
    private String email;
    private String password;


}
