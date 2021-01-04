package ru.test_api.sweater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Message;
import ru.test_api.sweater.entity.Tag;

public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByTags(Tag tags);    
}
