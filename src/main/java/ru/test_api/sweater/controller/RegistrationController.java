package ru.test_api.sweater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.service.UserService;

@RestController
@RequestMapping("auth")
public class RegistrationController {

    @Autowired
    private UserService userService;



    @GetMapping
    public List<Author> authorList() {return userService.findAll();}

    @PostMapping
    public ResponseEntity<String> userCreate(@RequestBody Author author) {
        if (!userService.saveAuthor(author)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        } else {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
