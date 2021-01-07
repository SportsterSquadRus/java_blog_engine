package ru.test_api.java_blog_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.java_blog_engine.entity.Message;
import ru.test_api.java_blog_engine.entity.Tag;

public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByTags(Tag tags);    
}
