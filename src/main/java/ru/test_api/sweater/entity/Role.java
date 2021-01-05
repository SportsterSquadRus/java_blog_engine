package ru.test_api.sweater.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

    /**
     *
     */
    private static final long serialVersionUID = 9002732335634511369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {return this.id;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}


    @Override
    public String getAuthority() {
        return getName();
    }
    
}
