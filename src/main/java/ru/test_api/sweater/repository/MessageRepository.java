package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    
}
