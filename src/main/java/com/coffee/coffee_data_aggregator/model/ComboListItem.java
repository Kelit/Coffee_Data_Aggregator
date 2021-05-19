package com.coffee.coffee_data_aggregator.model;
public interface ComboListItem {
    Long getId();

    String getName();

    void setId(Long id);

    void setName(String name);

    //for pictures
    byte[] getFile();

    String getType();

    default String getRepr() {
        return getName();
    }
}