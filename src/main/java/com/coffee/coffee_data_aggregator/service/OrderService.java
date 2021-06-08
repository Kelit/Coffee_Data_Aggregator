package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Order;
import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.OrderRepository;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> listOrderItems(User user){
        return orderRepository.findByUser(user);
    }

//    public Integer addProduct(Integer productID,Integer quantity, User user){
    public void addProduct(Long productID,Integer quantity, User user){
        Integer addQuantity = quantity;
        Product product = productRepository.findById(productID).get();

        Order order = orderRepository.findByUserAndProduct(user,product);

        if(order != null){
            addQuantity = order.getQuanity() + quantity;
            order.setQuanity(addQuantity);
        }else {
            order = new Order();
            order.setQuanity(quantity);
            order.setUser(user);
            order.setProductOrder(product);
        }
        orderRepository.save(order);
    }

    public void removeProduct(Long productID, User user){
        orderRepository.deleteByUserAndProduct(user.getId(),productID);
    }

//    public double updateQuantity(Integer productID, Integer quantity ,User user){
    public void updateQuantity(Long productID, Integer quantity ,User user){
        orderRepository.updateQuantity(quantity,productID,user.getId());
        Product product = productRepository.findById(productID).get();
        double subtotal = product.getProductPrice() * quantity;
//        return subtotal;

    }
}
