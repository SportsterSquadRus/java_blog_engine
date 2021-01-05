package ru.test_api.sweater.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.test_api.sweater.entity.Role;
import ru.test_api.sweater.entity.Author;
import ru.test_api.sweater.repository.RoleRepository;
import ru.test_api.sweater.repository.AuthorRepository;

public class UserService implements UserDetailsService {


    private AuthorRepository userRepository;
    private RoleRepository roleRepository;
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

    public boolean saveUser(Author user) {
        Author dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser == null) {
            Role userRole = roleRepository.findByName("USER");
            if (userRole == null) {
                userRole = new Role("USER");
                
            }
            user.setRole(Collections.singleton(userRole));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
            
        } else {
            return false;            
        }
    }
    
}
