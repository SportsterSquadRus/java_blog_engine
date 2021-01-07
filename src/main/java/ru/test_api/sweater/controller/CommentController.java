package ru.test_api.sweater.controller;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.entity.Comment;
import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.service.CommentService;
import ru.test_api.sweater.service.Views;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("{id}")
    @JsonView(Views.Basic.class)
    public Message commentCreate(@RequestBody Comment comment, @AuthenticationPrincipal Author author, @PathVariable Long id) {
        return commentService.commentCreate(id, author, comment);
    }    
}
