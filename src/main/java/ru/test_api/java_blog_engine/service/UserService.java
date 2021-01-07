package ru.test_api.java_blog_engine.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.test_api.java_blog_engine.entity.Role;
import ru.test_api.java_blog_engine.entity.Author;
import ru.test_api.java_blog_engine.repository.AuthorRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthorRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Author user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;            
        }        
    }

    public boolean saveAuthor(Author user) {
        Author dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser == null) {

            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
            
        } else {
            return false;            
        }
    }

    public List<Author> findAll() {
        return userRepository.findAll();
    }    
}
