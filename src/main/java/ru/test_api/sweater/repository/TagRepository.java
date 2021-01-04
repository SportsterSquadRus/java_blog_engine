package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String>{
    
}
