package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findByUsername(String username);
    
}
