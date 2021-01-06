package ru.test_api.sweater.entity;

import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.test_api.sweater.service.Views;

@Entity
public class Author implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -5679283692855195939L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.FullInfo.class)
    private Long id;

    @JsonView(Views.Basic.class)
    private String username;

    @JsonView(Views.FullInfo.class)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Basic.class)
    private Set<Role> roles;

    public Author() {
    }

    public Author(String name, String pass) {
        this.password = pass;
        this.username = name;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return this.username;
    }
    
}
