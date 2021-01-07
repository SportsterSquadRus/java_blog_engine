package ru.test_api.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.entity.Comment;
import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.repository.CommentRepository;
import ru.test_api.sweater.repository.MessageRepository;

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
