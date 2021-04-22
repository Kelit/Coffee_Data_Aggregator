package com.coffee.coffee_data_aggregator.service;

//import com.coffee.coffee_data_aggregator.model.SCart;
import com.coffee.coffee_data_aggregator.model.Role;
import com.coffee.coffee_data_aggregator.model.User;
//import com.coffee.coffee_data_aggregator.repository.SCartRepository;
import com.coffee.coffee_data_aggregator.repository.RoleRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Search
    public List<User> findAllUsers(){ return userRepository.findAll();}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public List<String> getRoleList(User user){
        List<String> roleList = List.of("ADMIN", "USER", "MODER");
        return roleList;
    }

    @Transactional
    public void deletUser(Long id){
        if(id == null || id == 0) return;
        User user = userRepository.findById(id).get();

        System.out.println("ПОЛЬЗОВАТЕЛЬ ПОД НИКОМ " + user.getUsername() + " Удален");
        userRepository.delete(user);
    }
    // Add
    @Transactional
    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername()); // найти по номеру, мылу
        if(userFromDb != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
//        User newUser = userRepository.save(user);
//        SCart saveCart = cartRepository.save(new SCart(newUser));
//        newUser.setSCart(saveCart);
        userRepository.save(user);
        System.out.println("Пользователь сохранен - " + user.getId() + ":" + user.getUsername());
        return true;
    }
    // Save
    @Transactional
    public void saveUser(User selectedUser, List<String> selectedUserRoles){
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

        Set<Role> roleList = getRoles(user, selectedUserRoles);

        for(var r : user.getRoles()){
            roleRepo.delete(r);
        }

        user.setRoles(roleList);

        System.out.println("Новые роли:");
        user.getRoles().forEach(r -> System.out.println(r.getRole_name()));
        System.out.println("Данные изменены:" + user.getUsername() + " :" + user.isActive());

        userRepository.save(user);
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
