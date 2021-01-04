package ru.test_api.sweater.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id
    private String tagName;

    public Tag() {}

    public Tag(String tagName) {this.tagName = tagName;}

    public String getTagName() {return this.tagName;}

    public void setTagName(String tagName) {this.tagName = tagName;}    
}
