package ru.test_api.java_blog_engine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import ru.test_api.java_blog_engine.service.Views;


@Entity
public class Tag {
    @Id
    @JsonView(Views.Basic.class)
    private String tagName;

    public Tag() {}

    public Tag(String tagName) {this.tagName = tagName.toLowerCase();}

    public String getTagName() {return this.tagName;}

    public void setTagName(String tagName) {this.tagName = tagName.toLowerCase();}  
    
    public String toString() {return this.tagName;}
}
