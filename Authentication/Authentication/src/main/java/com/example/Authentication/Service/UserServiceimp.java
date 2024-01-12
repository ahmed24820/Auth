package com.example.Authentication.Service;

import com.example.Authentication.Repository.UserRepo;
import com.example.Authentication.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceimp implements UserService, UserDetailsService {
    private final UserRepo userRepo;


    public UserServiceimp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveuser(User user)
    {
        return userRepo.save(user);
    }




    @Override
    public Optional<User> getuser(String username) {
        return userRepo.findByEmail(username);
    }

    @Override
    public List<User> Getallusers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user is not founded"));

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}