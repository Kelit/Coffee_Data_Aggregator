package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
public class Order  implements Serializable {
    @Id
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "bill"))
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id")
    private User userAccount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = OrderedProduct.class, mappedBy = "order")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy ="pk.order", cascade =
//            {CascadeType.PERSIST, CascadeType.MERGE})
//    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
    private Set<OrderedProduct> orderedProducts = new HashSet<>(0);

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bill bill;

    @Column(name = "products_cost", nullable = false)
    private double productsCost;

    @Column(name = "date_created", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dateCreated;

    @Column(name = "executed", nullable = false)
    private boolean executed;

    @Getter
    @Setter
    public static class Builder {
        private Long id;
        private User userAccount;
        private Set<OrderedProduct> orderedProducts = new HashSet<>();
        private Bill bill;
        private double productsCost;
        private Date dateCreated;
        private int deliveryCost;
        private boolean deliveryIncluded;
        private boolean executed;

        public Order build() {
            Order order = new Order();
            order.id = id;
            order.userAccount = userAccount;
            order.orderedProducts = orderedProducts;
            order.bill = bill;
            order.productsCost = productsCost;
            order.dateCreated = dateCreated;
            order.deliveryCost = deliveryCost;
            order.deliveryIncluded = deliveryIncluded;
            order.executed = executed;
            return order;
        }

        public Builder setProductsCostt(double productsCost) {
            this.productsCost = productsCost;
            return this;
        }

        public Builder setOrderedProducts(Set<OrderedProduct> orderedProducts) {
            this.orderedProducts = orderedProducts;
            return this;
        }

    }
}
