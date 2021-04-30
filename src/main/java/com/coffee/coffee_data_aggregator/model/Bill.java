package com.coffee.coffee_data_aggregator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "bill")
@Getter
@Setter
public  class Bill  implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "total_cost", nullable = false)
    private double totalCost;

    @Column(name = "payed", nullable = false)
    private boolean payed = false;

    @Column(name = "cc_number", nullable = false)
    @NotEmpty
    @Pattern(regexp = "\\b(?:\\d[ -]*?){13,16}\\b")
    private String ccNumber;

    @Column(name = "date_created", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dateCreated;


    @Getter
    @Setter
    public static class Builder {
        private Long id;
        private Order order;
        private int number;
        private double totalCost;
        private boolean payed = false;
        private String ccNumber;
        private Date dateCreated;

        public Bill build() {
            Bill bill = new Bill();
            bill.id = id;
            bill.order = order;
            bill.number = number;
            bill.totalCost = totalCost;
            bill.payed = payed;
            bill.ccNumber = ccNumber;
            bill.dateCreated = dateCreated;
            return bill;
        }

    }
}
