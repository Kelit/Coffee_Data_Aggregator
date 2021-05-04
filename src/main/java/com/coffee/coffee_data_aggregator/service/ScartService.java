package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.OrderMain;
import com.coffee.coffee_data_aggregator.model.ProductInOrder;
import com.coffee.coffee_data_aggregator.model.SCart;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.OrderMainRepository;
import com.coffee.coffee_data_aggregator.repository.ProductInOrderRepository;
import com.coffee.coffee_data_aggregator.repository.SCartRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class ScartService {

    @Autowired
    OrderMainRepository orderMainRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    SCartRepository cartRepository;

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    public SCart getCart(User user) {return user.getScart();}

    @Transactional
    public void delete(String itemId, User user) {
        var op = user.getScart().getProducts().stream().
                                                filter(e -> itemId.equals(e.getProductId())).
                                                findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setScart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Transactional
    public void checkout(User user) {
        // Создать заказа
        OrderMain order = new OrderMain(user);
        orderMainRepository.save(order);


        user.getScart().getProducts().forEach(productInOrder -> {
            productInOrder.setScart(null);
            productInOrder.setOrderMain(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
    }

    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        SCart finalCart = user.getScart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
            if (((Optional<?>) old).isPresent()) {
                prod = old.get();
                prod.setCount(productInOrder.getCount() + prod.getCount());
            } else {
                prod = productInOrder;
                prod.setScart(finalCart);
                finalCart.getProducts().add(prod);
            }
            productInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);
    }
}
