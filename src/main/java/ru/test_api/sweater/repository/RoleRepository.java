package ru.test_api.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.test_api.sweater.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
    
}
