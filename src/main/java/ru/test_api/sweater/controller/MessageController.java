package ru.test_api.sweater.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.repository.MessageRepository;

@RestController
@RequestMapping("message")
public class MessageController {
    private MessageRepository msgRepository;

    public MessageController(MessageRepository msgRepository) {this.msgRepository = msgRepository;}

    @GetMapping
    public List<Message> messageList() {return msgRepository.findAll();}

    @GetMapping("{id}")
    public Message messageDetail(@PathVariable Long id) {
        return msgRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public Message messageCreate(@RequestBody Message msg) {return msgRepository.save(msg);}
    
}
