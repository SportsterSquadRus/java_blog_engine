package ru.test_api.java_blog_engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import ru.test_api.java_blog_engine.entity.Author;
import ru.test_api.java_blog_engine.entity.Comment;
import ru.test_api.java_blog_engine.entity.Message;
import ru.test_api.java_blog_engine.repository.CommentRepository;
import ru.test_api.java_blog_engine.repository.MessageRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MessageRepository msgRepository;

    public Message commentCreate(Long id, Author author, Comment comment) {
        if (msgRepository.findById(id).isPresent()) {
            comment.setAuthor(author);  
            Message dbMsg = msgRepository.getOne(id);    
            dbMsg.addComment(commentRepository.save(comment));
            return dbMsg;            
        } else {
            throw new ResourceNotFoundException();            
        }        
    }    
}
