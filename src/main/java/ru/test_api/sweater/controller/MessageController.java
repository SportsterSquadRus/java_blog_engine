package ru.test_api.sweater.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.entity.Tag;
import ru.test_api.sweater.repository.MessageRepository;
import ru.test_api.sweater.repository.TagRepository;

@RestController
@RequestMapping("message")
public class MessageController {
    private MessageRepository msgRepository;
    private TagRepository tagRepository;

    public MessageController(MessageRepository msgRepository, TagRepository tagRepository) {
        this.msgRepository = msgRepository; 
        this.tagRepository = tagRepository;
    }


    @GetMapping
    public List<Message> messageList() {return msgRepository.findAll();}

    @GetMapping("{id}")
    public Message messageDetail(@PathVariable Long id) {
        return msgRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public Message messageCreate(@RequestBody Message msg) {
        Set<Tag> newTags = msg.getTags();
        
        Set<Tag> tags = newTags.stream().filter(tag -> tagRepository.findById(tag.getTagName()).isEmpty()).collect(Collectors.toSet());
        tagRepository.saveAll(tags);

        return msgRepository.save(msg);
    }
    
}
