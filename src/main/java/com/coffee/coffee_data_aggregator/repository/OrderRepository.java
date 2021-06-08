package com.coffee.coffee_data_aggregator.repository;

import com.coffee.coffee_data_aggregator.model.Order;
import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByUser(User user);
    public Order findByUserAndProduct(User user, Product product);
    @Query("UPDATE Order c set c.quanity =?1 " +
            "where c.productOrder.id = ?2" +
            " and c.user.id = ?3")
    @Modifying
    public void updateQuantity(Integer quantity, Long productID, Long userID);

    @Query("DELETE FROM Order c WHERE c.user.id =?1 " +
            "AND c.productOrder.id = ?2")
    @Modifying
    public void deleteByUserAndProduct(Long userId, Long productId);
}
