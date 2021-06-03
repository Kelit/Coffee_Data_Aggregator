package com.coffee.coffee_data_aggregator.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseFile implements Serializable {
    private String id;
    private String name;
    private String type;
    private byte[] size;

    public ResponseFile(String id, String name, String type, byte[] size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
    }
}
