package ru.test_api.sweater.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.test_api.sweater.entity.Role;
import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.repository.AuthorRepository;

@Service
public class UserService implements UserDetailsService {


    private AuthorRepository userRepository;
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
    
}
