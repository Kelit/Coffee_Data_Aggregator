package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductInOrder;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderService {

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Transactional
    public void update(String itemId, Integer quantity, User user) {
        var op = user.getScart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });

    }


    public ProductInOrder findOne(String itemId, User user) {
        var op = user.getScart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }

}
