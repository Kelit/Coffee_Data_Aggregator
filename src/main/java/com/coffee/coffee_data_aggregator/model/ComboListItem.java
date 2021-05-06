package com.coffee.coffee_data_aggregator.model;
public interface ComboListItem {
    Long getId();

    String getName();

    void setId(Long id);

    void setName(String name);

    default String getRepr() {
        return getName();
    }
}