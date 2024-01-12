package com.example.Authentication.AuthenticationController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService Authservice;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){

        return ResponseEntity.ok(Authservice.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(Authservice.authenticate(request));
    }

}