package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean active;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usr", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public  boolean isActive(){ return active;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() { return password;}
    @Override
    public String getUsername() { return username;}
    @Override
    public boolean isAccountNonExpired() { return isActive(); }
    @Override
    public boolean isAccountNonLocked() { return  isActive(); }
    @Override
    public boolean isCredentialsNonExpired() { return isActive(); }
    @Override
    public boolean isEnabled() { return isActive(); }




}