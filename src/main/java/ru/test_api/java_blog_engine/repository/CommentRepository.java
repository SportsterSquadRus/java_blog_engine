package ru.test_api.java_blog_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.java_blog_engine.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
