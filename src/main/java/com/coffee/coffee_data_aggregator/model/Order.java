package com.coffee.coffee_data_aggregator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "order_table")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate dateCreated;
    private String status;

    @ManyToOne
    private User user;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "pk.order")
//    @Valid
//    private List<OrderProduct> orderProducts = new ArrayList<>();
}
