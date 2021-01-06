package ru.test_api.sweater.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tags", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "message_id")})
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Message() {}

    public Message(String text) {this.text = text;}

    public Message(String text, Set<Tag> tags) {this.text = text; this.tags = tags;}

    public Long getId() {return this.id;}

    public String getText() {return this.text;}

    public Set<Tag> getTags() {return this.tags;}

    public Author getAuthor() {return author;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public void setText(String text) {this.text = text;}
    
    public void setTags(Set<Tag> tag) {this.tags = tag;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    public void setAuthor(Author author) {this.author = author;}
}
