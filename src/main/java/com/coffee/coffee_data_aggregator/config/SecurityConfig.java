package com.coffee.coffee_data_aggregator.config;

import com.coffee.coffee_data_aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Security
    @Bean
    public UserDetailsService userDetailsSevice(){
        return new UserSecurityService();
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public DaoAuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider =
                 new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(userDetailsSevice());
         daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
         return daoAuthenticationProvider;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/users/**").hasAuthority("Admin")
//                .antMatchers("/categories/**","/brands/**").hasAnyAuthority("Admin", "Editor")
//                .antMatchers("/products/**").hasAnyAuthority("Admin", "Editor", "Salesperson","Shipper")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("email")
//                    .permitAll()
//                .and().logout().permitAll()
//                .and().rememberMe();
                .antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication().withUser("admin").
                password(getPasswordEncoder().
                        encode("1122")).
                roles("ADMIN");
    }
}