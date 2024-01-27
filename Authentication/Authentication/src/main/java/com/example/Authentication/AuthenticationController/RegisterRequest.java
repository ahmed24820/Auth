package com.example.Authentication.AuthenticationController;

import com.example.Authentication.models.UserRole;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;


    public RegisterRequest() {

    }
}
