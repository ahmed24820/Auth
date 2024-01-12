package com.example.Authentication.Security;

import com.example.Authentication.CustomFilter.Customauthenticationfilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                        REQUEST_MATCHERS1
                        ).permitAll().anyRequest().authenticated());
        http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(customAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

}  private static final String[] REQUEST_MATCHERS1 = {"api/admin/**",
            "/api/user/**",
            "api/v1/auth/**"};
}
