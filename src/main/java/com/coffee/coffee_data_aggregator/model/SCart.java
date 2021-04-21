package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "scart")
@Getter
@Setter
@NoArgsConstructor
public class SCart  implements Serializable {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true,
                mappedBy = "cart")
    private Set<ProductInOrder> products = new HashSet<>();

    public SCart(User user) {
        this.user  = user;
    }
}

