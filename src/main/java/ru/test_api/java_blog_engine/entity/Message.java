package ru.test_api.java_blog_engine.entity;

import java.time.LocalDateTime;
import java.util.List;
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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.annotations.CreationTimestamp;

import ru.test_api.java_blog_engine.service.Views;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic.class)
    private Long id;

    @JsonView(Views.Basic.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @JsonView(Views.Basic.class)
    private LocalDateTime creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tags", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "message_id")})
    @JsonView(Views.Basic.class)
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonView(Views.Basic.class)
    private Author author;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comments", joinColumns = {@JoinColumn(name = "comment_id")}, inverseJoinColumns = {@JoinColumn(name = "message_id")})
    @JsonView(Views.Basic.class)
    private List<Comment> comments;

    public Message() {}

    public Message(String text) {this.text = text;}

    public Message(String text, Set<Tag> tags) {this.text = text; this.tags = tags;}

    public Long getId() {return this.id;}

    public String getText() {return this.text;}

    public Set<Tag> getTags() {return this.tags;}

    public Author getAuthor() {return author;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public List<Comment> getComments() {return comments;}

    public void setText(String text) {this.text = text;}
    
    public void setTags(Set<Tag> tag) {this.tags = tag;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    public void setAuthor(Author author) {this.author = author;}

    public void setComments(List<Comment> comments) {this.comments = comments;}

    public void addComment(Comment comment) {
        this.comments.add(comment);        
    }

    @Override
    public String toString() {
        return getText();
    }


}
