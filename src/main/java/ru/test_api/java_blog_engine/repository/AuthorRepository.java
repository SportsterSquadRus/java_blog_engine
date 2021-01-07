package ru.test_api.java_blog_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.java_blog_engine.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findByUsername(String username);
    
}
