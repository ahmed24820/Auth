package com.example.Authentication.AuthenticationController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class Admin {

    @GetMapping("/say")
    public ResponseEntity<String>get_hello(){
       return ResponseEntity.ok("Get:: admin page");
    }
    @PostMapping
    public ResponseEntity<String>post_hello(){
        return ResponseEntity.ok("post:: admin page");
    }
    @PutMapping
    public ResponseEntity<String>put_hello(){
        return ResponseEntity.ok("put:: admin page");
    }


}
