package ru.test_api.java_blog_engine.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
