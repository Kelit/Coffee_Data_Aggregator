package com.coffee.coffee_data_aggregator.model;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user_table")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Boolean active;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Order> order;

    public User(String name, String lastname,
                String username, String email,
                String password, String phone,
                String active){
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = Boolean.valueOf(active);
    }


    public  boolean isActive(){ return active;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return (Collection<? extends GrantedAuthority>) getRole(); }
    @Override
    public String getUsername() { return getUsername(); }
    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }
    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return isActive();
    }
    @Override
    public boolean isEnabled() {
        return isActive();
    }
}