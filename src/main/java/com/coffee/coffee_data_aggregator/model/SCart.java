package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scart")
@Getter
@Setter
public class SCart  implements Serializable {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(0);

    public SCart(User user) {
        this.user  = user;
    }
    public SCart() {
        this(null);
    }

    public CartItem update(Product product, int newQuantity) {
        if (product == null)
            return null;

        CartItem updatedItem = null;
        if (newQuantity > 0) {
            CartItem existingItem = findItem(product.getId());
            if (existingItem == null) {
                CartItem newItem = new CartItem(this, product, newQuantity);
                cartItems.add(newItem);
                updatedItem = newItem;
            } else {
                existingItem.setQuantity(newQuantity);
                updatedItem = existingItem;
            }
        } else {
            removeItem(product.getId());
        }
        return updatedItem;
    }

    private void removeItem(long productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    private CartItem findItem(long productId) {
        for (CartItem existingItem : cartItems) {
            if (existingItem.getProduct().getId() == productId)
                return existingItem;
        }
        return null;
    }

    private double calculateItemsCost() {
        return cartItems.stream()
                .mapToDouble(CartItem::calculateCost)
                .sum();
    }

    public void clear() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        if(this.user == null) return  true;
        return false;
    }
}