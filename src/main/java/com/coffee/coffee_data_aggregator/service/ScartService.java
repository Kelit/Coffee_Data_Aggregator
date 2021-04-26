package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.CartItem;
import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.SCart;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import com.coffee.coffee_data_aggregator.repository.SCartRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class ScartService {

    @Autowired
    private SCartRepository sCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean getCartOrCreate(String username) {
        User account = userRepository.findByUsername(username); // todo: check if this user exists
        Optional<SCart> cartOptional = sCartRepository.findById(account.getId());
        cartOptional.orElseGet(() -> createCart(account));
        return true;
    }

    private SCart createCart(User account) {
        if (log.isDebugEnabled())
            log.debug("Creating new cart for account #" + account.getId());
        return sCartRepository.save(new SCart(account));
    }

    @Transactional
    public boolean addToCart(String userEmail, long productId, int quantity) {
        SCart cart = getCartOrCreate(userEmail);
        Product product = productRepository.getProduct(productId);
        if (product.isAvailable()) {
            cart.update(product, quantity);
            sCartRepository.save(cart);
            return true;
        } else {
            return cart;
        }
    }
}
