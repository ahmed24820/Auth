package com.example.Authentication.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

    @Getter
    @RequiredArgsConstructor
    public enum Permission{

        ADMIN_READ("admin:read"),
        ADMIN_WRITE("admin:write"),
        ADMIN_UPDATE("admin:update"),
        ADMIN_DELETE("admin:delete"),

        MANGER_READ("manager:read"),
        MANGER_WRITE("manager:write"),
        MANGER_UPDATE("manager:update"),
        MANGER_DELETE("manager:delete");

        private final String permission;
    }
