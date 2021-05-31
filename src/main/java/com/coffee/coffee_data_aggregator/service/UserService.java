package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import com.coffee.coffee_data_aggregator.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String user_role = "USER";

    @Transactional
    public void deleteUser(Long id){
        if(id == null || id == 0) return;
        User user = userRepository.findById(id).get();
        for(var r : user.getRole()) roleRepository.delete(r);
        System.out.println("Пользователь с username: " + user.getUsername() + " удален");
        userRepository.delete(user);
    }
    @Transactional
    public void addUser(User user){
//        // any safe ?
//        if(findById(user.getId()) != null){
//            if (!user.getUsername().equals(user.getUsername())) user.setUsername(user.getUsername());
//            if (!passwordEncoder.matches(user.getPassword(), user.getPassword())
//                    && !user.getPassword().equals(user.getPassword())
//                    && !StringUtils.isEmpty(user.getPassword())
//                    && user.getPassword() != null
//                    && user.getPassword().length() >= 3){
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//            }
//            if (user.isActive() != user.isActive()) user.setActive(user.isActive());
//            userRepository.save(user);
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAllUsers(){ return userRepository.findAll();}
    public Page<User> findAll(Pageable pageable){ return userRepository.findAll(pageable);}

    @Transactional
    public void saveUser(User selectedUser, List<String> selectedUserRole){
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

        Set<Role> roleList =getRoles(user, selectedUserRole);

        for(var r : user.getRole()){
            roleRepository.delete(r);
        }

        user.setRole(roleList);

        System.out.println("Новые роли:");
        user.getRole().forEach(r -> System.out.println(r.getRole_name()));
        System.out.println("Данные изменены:" + user.getUsername() + " :" + user.isActive());

//        user.setRole(roleRepository.findByRole(user_role));
        userRepository.save(user);
    }

    public User findById(Long obj) {
        return userRepository.findById(obj).get();
    }

    private Set<Role> getRoles(User user, List<String> selectedUserRoles) {
        Set<Role> roleList = new HashSet<>();
        for(var role : selectedUserRoles){
            Role r = new Role();
            r.setUser(user);
            r.setRole_name(role);
            roleList.add(r);
        }
        return roleList;
    }
}
