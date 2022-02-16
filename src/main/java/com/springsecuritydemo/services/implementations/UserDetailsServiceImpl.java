package com.springsecuritydemo.services.implementations;


import com.springsecuritydemo.data.entities.User;
import com.springsecuritydemo.data.persistance.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private com.springsecuritydemo.data.persistance.UserRepository UserRepository;

    public UserDetailsServiceImpl(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User User = UserRepository.findByUsername(username);

        if (User == null) {
            throw new UsernameNotFoundException(username);
        }
        return User;
    }
}
