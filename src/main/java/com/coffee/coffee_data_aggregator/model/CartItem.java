package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem  implements Serializable {

    @EmbeddedId
    private CartItemId pk = new CartItemId();

    @MapsId("cartId")
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SCart cart;

    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {}

    public CartItem(SCart cart, Product product, int quantity) {
        this.pk = new CartItemId(cart.getId(), product.getId());
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateCost() {
        return quantity * product.getPrice();
    }
}
