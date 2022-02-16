package com.springsecuritydemo.services;

import com.springsecuritydemo.config.security.MyBCryptPasswordEncoder;
import com.springsecuritydemo.models.Privilege;
import com.springsecuritydemo.models.Role;
import com.springsecuritydemo.models.User;
import com.springsecuritydemo.repositories.PrivilageRepository;
import com.springsecuritydemo.repositories.RoleRepository;
import com.springsecuritydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServices {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;

    @Autowired
    private PrivilageRepository privilageRepository;

    public void createUser(User user) {
        Role role = roleRepository.findByName("USER");
        if (role == null)
            role = roleRepository.save(Role.valueOf("USER"));

        Privilege privilege = privilageRepository.findByAuthority("READ_PROFILE");
        if (privilege == null)
            privilege = privilageRepository.save(new Privilege("READ_PROFILE"));
        role.getPrivileges().add(privilege);

        user.getRoles().add(role);
        user.setPassword(myBCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void createAdmin(User user) {
        Role role = roleRepository.findByName("ADMIN");
        if (role == null)
            roleRepository.save(Role.valueOf("ADMIN"));
        Privilege privilege = privilageRepository.findByAuthority("EDIT_PROFILE");
        if (privilege == null)
            privilege = privilageRepository.save(new Privilege("EDIT_PROFILE"));
        role.getPrivileges().add(privilege);
        privilege = privilageRepository.findByAuthority("READ_PROFILE");
        if (privilege == null)
            privilege = privilageRepository.save(new Privilege("READ_PROFILE"));
        role.getPrivileges().add(privilege);
        privilege = privilageRepository.findByAuthority("DELETE_PROFILE");
        if (privilege == null)
            privilege = privilageRepository.save(new Privilege("DELETE_PROFILE"));
        role.getPrivileges().add(privilege);
        user.getRoles().add(role);
        user.setPassword(myBCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
