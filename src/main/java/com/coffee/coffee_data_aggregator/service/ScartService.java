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

import java.util.List;
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

//    @Transactional(propagation = Propagation.SUPPORTS)
    public SCart getCartOrCreate(String username) {
        User account = userRepository.findByUsername(username);
        Optional<SCart> cartOptional = sCartRepository.findById(account.getId());
        return cartOptional.orElseGet(() -> createCart(account));
//        return true;
    }

    private SCart createCart(User account) {
        log.debug("Creating new cart for account #" + account.getId());
        return sCartRepository.save(new SCart(account));
    }
    @Transactional
    public boolean addToCart(String user, long productId, int quantity) {
        SCart cart = getCartOrCreate(user);
        Product product = productRepository.getProduct(productId);
        if (product.isAvailable()) {
            cart.update(product, quantity);
            sCartRepository.save(cart);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean addAllToCart(String userEmail, List<CartItem> itemsToAdd) {
        SCart cart = getCartOrCreate(userEmail);
        boolean updated = false;
        for (CartItem item : itemsToAdd) {
            Optional<Product> product = productRepository.findById(item.getProduct().getId());
            if (product.isPresent() && product.get().isAvailable()) {
                cart.update(product.get(), item.getQuantity());
                updated = true;
            }
        }
        if (updated) {sCartRepository.save(cart);}
        return true;
    }

    @Transactional
    public boolean clearCart(String userEmail) {
        SCart cart = getCartOrCreate(userEmail);
        cart.clear();
        sCartRepository.save(cart);
        return true;
    }
}
