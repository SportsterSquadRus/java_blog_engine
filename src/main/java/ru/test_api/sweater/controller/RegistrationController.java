package ru.test_api.sweater.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.repository.AuthorRepository;

@RestController
@RequestMapping("auth")
public class RegistrationController {
    private AuthorRepository userRepository;

    public RegistrationController(AuthorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Author> userList() {return userRepository.findAll();}

    
}
