package com.example.Authentication.CustomFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Service
public class Customauthenticationfilter extends OncePerRequestFilter {

    private final JWTservice jwtservice;
    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request
            ,@NonNull HttpServletResponse response
            ,@NonNull FilterChain filterChain) throws ServletException, IOException
    {
        final String authheader=request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(authheader == null||!authheader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authheader.substring(7);
        username=jwtservice.extractusername(jwt);
        if (username!=null|| SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
            if (jwtservice.isvalid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(
                        userDetails
                        ,null,
                        userDetails.getAuthorities());
                authtoken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
