package com.example.Authentication.AuthenticationController;

import com.example.Authentication.CustomFilter.JWTservice;
import com.example.Authentication.Repository.UserRepo;
import com.example.Authentication.models.User;
import com.example.Authentication.models.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private  final UserRepo userRepo;
    private final JWTservice jwTservice;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register( RegisterRequest registerRequest){
        User user=new User();
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getLastname());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        userRepo.save(user);

        var jwttoken=jwTservice.GenerateToken(user);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        var user=userRepo.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("not founed"));
        var jwttoken=jwTservice.GenerateToken(user);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();

    }

}
