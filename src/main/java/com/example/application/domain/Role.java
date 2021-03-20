package com.example.application.domain;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, MASTER, ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }

   public static Role parseRoleFromString(String role) throws Exception {
        switch (role){
            case("USER") : return Role.USER;
            case("MASTER") : return Role.MASTER;
            case("STUDENT") : return Role.STUDENT;
            case("ADMIN") : return Role.ADMIN;
            default: throw new Exception("Can't parse user's role");
        }
    }
}
