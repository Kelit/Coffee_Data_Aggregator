package com.coffee.coffee_data_aggregator.model;
/*
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ProductInOrder")
@Getter
@Setter
public class ProductInOrder {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "cart_id")
//    @JsonIgnore
    private SCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // ?
    @JsonIgnore
    private OrderMain orderMain;

    private String productId;
    private String productName;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
    private BigDecimal productPrice;
    private Integer productStock;
    private Integer count;

    public ProductInOrder(ProductInfo productInfo, Integer quantity) {
        this.productId = productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productDescription = productInfo.getProductDescription();
        this.productIcon = productInfo.getProductIcon();
        this.categoryType = productInfo.getCategoryType();
        this.productPrice = productInfo.getProductPrice();
        this.productStock = productInfo.getProductStock();
        this.count = quantity;
    }

    public ProductInOrder() {

    }
}
*/