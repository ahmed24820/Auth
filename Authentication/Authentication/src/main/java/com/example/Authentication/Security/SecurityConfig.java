package com.example.Authentication.Security;

import com.example.Authentication.CustomFilter.Customauthenticationfilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.Authentication.models.Permission.*;
import static com.example.Authentication.models.UserRole.Admin;
import static com.example.Authentication.models.UserRole.MANGER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
   private final Customauthenticationfilter customAuthenticationfilter;
    private final UserDetailsService userDetailsService;


    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req.requestMatchers(
                        "/api/user/**",
                        "api/v1/auth/**",
                        "api/v1/admin/**"
                        ).permitAll()

                        .requestMatchers("/api/Management/**").hasAnyRole(Admin.name(), MANGER.name())
                        .requestMatchers(GET,"/api/Management/**").hasAnyAuthority(ADMIN_READ.name(), MANGER_READ.name())
                        .requestMatchers(PUT,"/api/Management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANGER_UPDATE.name())
                        .requestMatchers(POST,"/api/Management/**").hasAnyAuthority(ADMIN_WRITE.name(), MANGER_WRITE.name())
                        .requestMatchers(DELETE,"/api/Management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANGER_DELETE.name())

                        .requestMatchers("/api/v1/admin/**").hasRole(Admin.name())
                        .requestMatchers(GET,"/api/v1/admin/**").hasAnyAuthority(ADMIN_READ.name())
                        .requestMatchers(PUT,"/api/v1/admin/**").hasAnyAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(POST,"/api/v1/admin/**").hasAnyAuthority(ADMIN_WRITE.name())
                        .requestMatchers(DELETE,"/api/v1/admin/**").hasAnyAuthority(ADMIN_DELETE.name())

                        .anyRequest()
                        .authenticated());
        http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(customAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

}}
