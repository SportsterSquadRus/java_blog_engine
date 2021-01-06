package ru.test_api.sweater.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Author;

import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.entity.Tag;
import ru.test_api.sweater.repository.MessageRepository;
import ru.test_api.sweater.repository.TagRepository;
import ru.test_api.sweater.service.Views;

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
    @JsonView(Views.Basic.class)
    public List<Message> messageList() {return msgRepository.findAll();}

    @GetMapping("{id}")
    @JsonView(Views.Basic.class)
    public Message messageDetail(@PathVariable Long id) {
        return msgRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("tag_filter")
    @JsonView(Views.Basic.class)
    public List<Message> filterMessage(@RequestParam String tag_name) {
        try {
            Tag tag = tagRepository.findByTagName(tag_name.toLowerCase());
            return msgRepository.findByTags(tag);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @PostMapping
    @JsonView(Views.Basic.class)
    public Message messageCreate(@RequestBody Message msg, @AuthenticationPrincipal Author author) {
        msg.setAuthor(author);
        tagRepository.saveAll(msg.getTags().stream().filter(tag -> tagRepository.findById(tag.getTagName()).isEmpty())
                .collect(Collectors.toSet()));

        return msgRepository.save(msg);
    }

}
