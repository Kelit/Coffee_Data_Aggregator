package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import com.coffee.coffee_data_aggregator.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String user_role = "USER";

    @Transactional
    public void deleteUser(Long id){
        if(id == null || id == 0) return;
        User user = userRepository.findById(id).get();

        System.out.println("Пользователь с username: " + user.getUsername() + " удален");
        userRepository.delete(user);
    }
    @Transactional
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userRoleRepository.fi);
        userRepository.save(user);
    }

    public List<User> findAllUsers(){ return userRepository.findAll();}
    public Page<User> findAll(Pageable pageable){ return userRepository.findAll(pageable);}



    @Transactional
    public void saveUser(User selectedUser){
//        User user = userRepository.findById(selectedUser.getId()).get();
        User user = findById(selectedUser.getId());

        if (!user.getUsername().equals(selectedUser.getUsername())) user.setUsername(selectedUser.getUsername());
        if (!passwordEncoder.matches(selectedUser.getPassword(), user.getPassword())
                && !user.getPassword().equals(selectedUser.getPassword())
                && !StringUtils.isEmpty(selectedUser.getPassword())
                && selectedUser.getPassword() != null
                && selectedUser.getPassword().length() >= 3){
            user.setPassword(passwordEncoder.encode(selectedUser.getPassword()));
        }

        if (user.isActive() != selectedUser.isActive()) user.setActive(selectedUser.isActive());

        user.setRole(roleRepository.findByRole(user_role));

        userRepository.save(user);
    }

    public User findById(Long obj) {
        return userRepository.findById(obj).get();
    }
}
