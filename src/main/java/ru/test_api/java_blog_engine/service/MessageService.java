package ru.test_api.java_blog_engine.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import ru.test_api.java_blog_engine.entity.Author;
import ru.test_api.java_blog_engine.entity.Message;
import ru.test_api.java_blog_engine.repository.AuthorRepository;
import ru.test_api.java_blog_engine.repository.MessageRepository;
import ru.test_api.java_blog_engine.repository.TagRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository msgRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Message> findAll() {return msgRepository.findAll();}

    public Message findByMessageId(Long id) {
        return msgRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Message saveMessage(Message msg) {
        messageTagsSaver(msg);
        return msgRepository.save(msg);
    }

    public boolean deleteMessage(Long id, Author author) {
        if (messageIdAndAuthorComparison(id, author)) {
            msgRepository.deleteById(id);
            return true;            
        } else {
            return false;            
        }
    }

    public boolean messageIdAndAuthorComparison(Long id, Author author) {
        if (msgRepository.findById(id)
                            .isPresent() && msgRepository
                                                        .getOne(id)
                                                        .getAuthor()
                                                        .equals(authorRepository
                                                        .findByUsername(author.getUsername()))) {
            return true;
        } else {
            return false;
        }
        
    }

    public void messageTagsSaver(Message msg) {
        tagRepository.saveAll(msg.getTags().stream().filter(tag -> tagRepository.findById(tag.getTagName()).isEmpty())
        .collect(Collectors.toSet()));
    }


    public Message messageUpdate(Long id, Message msg) {
        Message dbMsg = msgRepository.getOne(id);

        dbMsg.setTags(msg.getTags());
        dbMsg.setText(msg.getText());
        return dbMsg;
    }

    public List<Message> filterMessageByTag(String tagName) {
        try {
            return msgRepository.findByTags(tagRepository.findByTagName(tagName.toLowerCase()));
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }

    }

    
}
