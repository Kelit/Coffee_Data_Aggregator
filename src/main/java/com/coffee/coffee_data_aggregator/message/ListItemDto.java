package com.coffee.coffee_data_aggregator.message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListItemDto {
    private Long id;
    private String value;
}
