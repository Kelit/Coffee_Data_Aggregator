package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.RoleRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String user_role = "USER_ROLE";

    @Transactional
    public void deletUser(Long id){
        if(id == null || id == 0) return;
        User user = userRepository.findById(id).get();

        System.out.println("Пользователь с username: " + user.getUsername() + " удален");
        userRepository.delete(user);
    }
    @Transactional
    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("Пользователь сохранен - " + user.getId() + ":" + user.getUsername());
        return true;
    }

    public List<User> findAllUsers(){ return userRepository.findAll();}
    public Page<User> findAll(Pageable pageable){ return userRepository.findAll(pageable);}



    @Transactional
    public void saveUser(User selectedUser, String role){
        User user = userRepository.findById(selectedUser.getId()).get();

        if (!user.getUsername().equals(selectedUser.getUsername())) user.setUsername(selectedUser.getUsername());
        if (!passwordEncoder.matches(selectedUser.getPassword(), user.getPassword())
                && !user.getPassword().equals(selectedUser.getPassword())
                && !StringUtils.isEmpty(selectedUser.getPassword())
                && selectedUser.getPassword() != null
                && selectedUser.getPassword().length() >= 3){
            user.setPassword(passwordEncoder.encode(selectedUser.getPassword()));
        }

        if (user.isActive() != selectedUser.isActive()) user.setActive(selectedUser.isActive());

        user.setRole(roleRepository.findByRole(role));

        userRepository.save(user);
    }
}
