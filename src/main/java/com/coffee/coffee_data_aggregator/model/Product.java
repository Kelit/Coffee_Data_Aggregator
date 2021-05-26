package com.coffee.coffee_data_aggregator.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product implements ComboListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIdentityReference
    @JsonSerialize(as=ComboListItem.class)
    private ProductCategory category;

    private BigDecimal productPrice;

    private String productDescription;

    @ManyToOne
    @JsonIdentityReference
    @JsonSerialize(as=ComboListItem.class)
    private ImageModel productIcon;

    @Lob
    private byte[] icon;

    @CreationTimestamp
    private Date createTime;

    @Override
    public byte[] getFile() {
        return new byte[0];
    }

    @Override
    public String getType() {
        return null;
    }
}