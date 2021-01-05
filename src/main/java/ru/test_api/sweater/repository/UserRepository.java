package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    
}
