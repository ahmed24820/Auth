package com.example.Authentication.AuthenticationController;

import com.example.Authentication.Service.UserServiceimp;
import com.example.Authentication.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserServiceimp userService;

    private final AuthService Authservice;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){

        return ResponseEntity.ok(Authservice.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(Authservice.authenticate(request));
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getusers()
    {
        return ResponseEntity.ok().body(userService.Getallusers());

    }
    @GetMapping("/greeting")
    public ResponseEntity<String> sayinghello(){
       return ResponseEntity.ok("hello from ahmed");
    }

}