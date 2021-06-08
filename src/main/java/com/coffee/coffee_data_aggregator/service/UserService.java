package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.RoleRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }
    public List<Role> listRoles{ return (List<Role>)roleRepository.findAll(); }

    public void save(User user){
        userRepository.save(user);
    }

}
