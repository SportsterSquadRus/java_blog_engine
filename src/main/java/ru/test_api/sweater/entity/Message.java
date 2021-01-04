package ru.test_api.sweater.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Message() {}

    public Message(String text) {this.text = text;}

    public Long getId() {return this.id;}

    public String getText() {return this.text;}

    public void setText(String text) {this.text = text;}
    
}
