package ru.test_api.java_blog_engine.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.annotations.CreationTimestamp;

import ru.test_api.java_blog_engine.service.Views;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic.class)
    private Long id;

    @JsonView(Views.Basic.class)
    private String text;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.Basic.class)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonView(Views.Basic.class)
    private Author author;

    public Comment() {}

    public Comment(String text) {this.text = text;}

    public Long getId() {return id;}

    public String getText() {return text;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public Author getAuthor() {return author;}

    public void setText(String text) {this.text = text;}

    public void setAuthor(Author author) {this.author = author;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}    
}
