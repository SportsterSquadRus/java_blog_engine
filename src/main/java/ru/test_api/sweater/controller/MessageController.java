package ru.test_api.sweater.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.service.MessageService;
import ru.test_api.sweater.service.Views;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService msgServise;

    @GetMapping
    @JsonView(Views.Basic.class)
    public List<Message> messageList() {
        return msgServise.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.Basic.class)
    public Message messageDetail(@PathVariable Long id) {
        return msgServise.findByMessageId(id);
    }

    @GetMapping("tag_filter")
    @JsonView(Views.Basic.class)
    public List<Message> filterMessage(@RequestParam String tag_name) {
        return msgServise.filterMessageByTag(tag_name);
    }

    @PostMapping
    @JsonView(Views.Basic.class)
    public Message messageCreate(@RequestBody Message msg, @AuthenticationPrincipal Author author) {
        msg.setAuthor(author);
        return msgServise.saveMessage(msg);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> messageDelete (@PathVariable Long id, @AuthenticationPrincipal Author author) {
        if (msgServise.deleteMessage(id, author)) {              
            return new ResponseEntity<>(HttpStatus.OK);            
        } else {            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);            
        }        
    }

    @JsonView(Views.Basic.class)
    @PutMapping("{id}")
    public Message editMessage(@PathVariable Long id, @RequestBody Message msg, @AuthenticationPrincipal Author author) {

        if (msgServise.messageIdAndAuthorComparison(id, author)) {
            
            msgServise.messageTagsSaver(msg);
            return msgServise.messageUpdate(id, msg);

        } else {
            throw new ResourceNotFoundException();            
        }
        

    }
}
