package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

// This primary key of the table CartItem
@Embeddable
@Getter
@Setter
public class CartItemId implements Serializable {
    private long cartId;
    private long productId;

    public CartItemId() {
    }

    public CartItemId(long cartId, long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }
}
