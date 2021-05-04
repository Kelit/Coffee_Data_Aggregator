package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.SCart;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.SCartRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SCartRepository cartRepository;

    public User findOne(String email) { return userRepository.findByEmail(email);}

    public Collection<User> findByRole(String role) { return userRepository.findAllByRole(role); }

    @Transactional
    public User save(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            SCart savedCart = cartRepository.save(new SCart(savedUser));
            savedUser.setScart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
//            throw new MyException(ResultEnum.VALID_ERROR);
            throw new Exception();
        }

    }

    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setUsername(user.getUsername());
        oldUser.setPhone(user.getPhone());
        return userRepository.save(oldUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){ throw new UsernameNotFoundException("Пользователь не найден");}
        return user;
    }

    public List<User> findAllUsers(){ return userRepository.findAll();}
    public  String getRoleList(User user){ return user.getRole();}


    public void deletUser(Long id) {
        if(id == null || id == 0) return;

        User user = (User) userRepository.findById(id);
        System.out.println(user.getUsername() + " удален");
        userRepository.delete(user);
    }
}
