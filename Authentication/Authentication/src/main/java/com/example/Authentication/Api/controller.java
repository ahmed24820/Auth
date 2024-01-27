package com.example.Authentication.Api;

import com.example.Authentication.Service.UserService;
import com.example.Authentication.Service.UserServiceimp;
import com.example.Authentication.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@AllArgsConstructor

public class controller {
    private final UserServiceimp userService;

        @GetMapping("/users")
        public ResponseEntity<List<User>> getusers()
        {
            return ResponseEntity.ok().body(userService.Getallusers());

        }
        @PostMapping("/user/save")
        public ResponseEntity <User>saveuser(@RequestBody User user )
        {
            URI Uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
            return ResponseEntity.created(Uri).body(userService.saveuser(user));
        }

}
