package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordered_product")
@Getter
@Setter
public class OrderedProduct implements Serializable {
    @EmbeddedId
    private OrderedProductId pk = new OrderedProductId();

    @MapsId("orderId")
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Getter
    @Setter
    public static class Builder {
        private Order order;
        private Product product;
        private int quantity;

        public OrderedProduct build() {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setOrder(order);
            orderedProduct.setProduct(product);
            orderedProduct.setQuantity(quantity);
            return orderedProduct;
        }

    }
}
