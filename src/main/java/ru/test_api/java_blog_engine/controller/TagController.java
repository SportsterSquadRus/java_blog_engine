package ru.test_api.java_blog_engine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.java_blog_engine.entity.Tag;
import ru.test_api.java_blog_engine.repository.TagRepository;

@RestController
@RequestMapping("tag")
public class TagController {
    private TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {this.tagRepository = tagRepository;}

    @GetMapping
    public List<Tag> tagList() {return tagRepository.findAll();}
}
