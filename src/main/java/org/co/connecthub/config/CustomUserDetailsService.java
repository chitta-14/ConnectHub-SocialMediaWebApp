package org.co.connecthub.config;

import org.co.connecthub.entity.User;
import org.co.connecthub.exception.ResourceNotFoundException;
import org.co.connecthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email : "+username ,0));
        return user;
    }
}
