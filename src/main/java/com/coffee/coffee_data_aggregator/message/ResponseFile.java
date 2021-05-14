package com.coffee.coffee_data_aggregator.message;

import lombok.Data;

@Data
public class ResponseFile {
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
