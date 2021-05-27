package com.coffee.coffee_data_aggregator.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseUser implements Serializable {

    private String id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String phone;
    private String active;

    public ResponseUser(String id, String username, String email,
                        String name, String lastname, String phone, String active){
            this.id = id;
            this.username = username;
            this.email = email;
            this.name = name;
            this.lastname = lastname;
            this.phone = phone;
            this.active  = active;
    }
}
