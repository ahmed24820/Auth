package com.example.Authentication.CustomFilter;

import com.example.Authentication.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class appconfig {
    private final UserRepo userRepo;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByEmail(username).
                orElseThrow(()-> new UsernameNotFoundException("the user is not founded"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider()throws Exception {
        DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
        daoauth.setUserDetailsService(userDetailsService());
        daoauth.setPasswordEncoder(bCryptPasswordEncoder());
        return daoauth;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}