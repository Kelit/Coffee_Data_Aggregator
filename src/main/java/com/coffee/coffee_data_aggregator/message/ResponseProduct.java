package com.coffee.coffee_data_aggregator.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseProduct implements Serializable {
    private String id;
    private String name;
    private String category;
    private String productPrice;
    private String productStock;
    private String productDescription;
    private String createTime;
    private String updateTime;
    private byte[] productIcon;

    public ResponseProduct(String id,
                           String name,
                           String category,
                           String productPrice,
                           String productDescription,
                           String createTime,
                           byte[] productIcon){
                this.id = id;
                this.name = name;
                this.category = category;
                this.productPrice = productPrice;
                this.productDescription = productDescription;
                this.createTime = createTime;
                this.productIcon = productIcon;
    }
}
