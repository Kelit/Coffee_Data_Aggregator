package com.coffee.coffee_data_aggregator.controllers;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListItemDto {
    private Long id;
    private String value;
}
