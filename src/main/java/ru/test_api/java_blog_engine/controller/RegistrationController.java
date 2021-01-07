package ru.test_api.java_blog_engine.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.java_blog_engine.entity.Author;
import ru.test_api.java_blog_engine.service.UserService;
import ru.test_api.java_blog_engine.service.Views;

@RestController
@RequestMapping("auth")
public class RegistrationController {

    private final ResponseEntity<String> notAcceptedStatus = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    private final ResponseEntity<String> acceptedStatus = new ResponseEntity<>(HttpStatus.ACCEPTED);

    @Autowired
    private UserService userService;

    @JsonView(Views.FullInfo.class)
    @GetMapping
    public List<Author> authorList() {return userService.findAll();}

    @PostMapping
    public ResponseEntity<String> userCreate(@RequestBody Author author) {
        if (!userService.saveAuthor(author)) {
            return notAcceptedStatus;

        } else {
            return acceptedStatus;
        }
    }
}
