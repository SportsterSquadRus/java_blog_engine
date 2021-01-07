package ru.test_api.java_blog_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.java_blog_engine.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String>{
    Tag findByTagName(String tagName);
    
}
