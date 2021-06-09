package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.RoleRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long id){ return userRepository.findById(id.longValue()).get();}
    public List<User> listAll(){
        return userRepository.findAll();
    }
    public List<Role> listRoles(){ return roleRepository.findAll(); }
    public void save(User user){
        if(user.getId() != null) {
            User usr = userRepository.findById(user.getId()).get();
            if(user.getPassword().isEmpty())
                user.setPassword(usr.getPassword());
            else
                passwordEncoder.encode(user.getPassword());
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    public void deleteUser(Long id){
        // TODO User check
        userRepository.delete(userRepository.findById(id).get());
    }

    public void updateUserActive(Long id, boolean active){
        userRepository.updateActive(id, active);
    }

    public boolean isEmailUnique(String email){
        User usr = userRepository.getUsersByEmail(email);
        return usr == null;
    }
}
