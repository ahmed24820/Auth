package com.example.Authentication.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Authentication.models.Permission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    User(Collections.emptySet()),
    Admin(
            Set.of(
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    ADMIN_WRITE,
                    ADMIN_READ,

                    MANGER_DELETE,
                    MANGER_UPDATE,
                    MANGER_WRITE,
                    MANGER_READ
            )
    ),
    MANGER(
            Set.of(
                    MANGER_DELETE,
                    MANGER_UPDATE,
                    MANGER_WRITE,
                    MANGER_READ
            )

    )
    ;

        private final Set<Permission> permissions;
        public List<SimpleGrantedAuthority> Authorities(){
            var auth= getPermissions().
                    stream().
                    map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                            .collect(Collectors.toList());
              auth.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
            return auth;
        }

    }


