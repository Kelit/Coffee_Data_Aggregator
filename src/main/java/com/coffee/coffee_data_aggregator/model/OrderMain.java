package com.coffee.coffee_data_aggregator.model;
/*
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.coffee.coffee_data_aggregator.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class OrderMain  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "orderMain")
    private Set<ProductInOrder> products = new HashSet<>();


    private String buyerEmail;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    // Total Amount
    private BigDecimal orderAmount;
    @ColumnDefault("0")
    private Integer orderStatus;
    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public OrderMain(User buyer) {
        this.buyerEmail = buyer.getEmail();
        this.buyerName = buyer.getUsername();
        this.buyerPhone = buyer.getPhone();
//        this.buyerAddress = buyer.getAddress();
        this.orderAmount = buyer.getSCart().getProducts().stream().map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        this.orderStatus = 0;
    }

    public OrderMain() {

    }
}
*/