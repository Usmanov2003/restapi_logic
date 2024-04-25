package com.example.rest_apis.security;

import com.example.rest_apis.exceptions.ResourceNotFoundException;
import com.example.rest_apis.repository.UserRepository;
import com.example.rest_apis.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = (User) this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email:" + username, 0));

        return user;
    }
}
