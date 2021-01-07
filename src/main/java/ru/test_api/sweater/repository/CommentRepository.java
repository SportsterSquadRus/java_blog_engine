package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
