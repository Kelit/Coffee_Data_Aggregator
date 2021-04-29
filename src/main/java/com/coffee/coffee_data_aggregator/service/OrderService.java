package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Bill;
import com.coffee.coffee_data_aggregator.model.Order;
import com.coffee.coffee_data_aggregator.model.SCart;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.repository.OrderRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScartService scartService;

    @Transactional
    public List<Order> getUserOrders(String user) {
        User account = userRepository.findByUsername(user);
        return orderRepository.findByUserAccountOrderByDateCreatedDesc(account);
    }

    @Transactional
    public Optional<Order> getUserOrder(String userLogin, long orderId) {
        // todo: add user check
        return orderRepository.findById(orderId);
    }

//    @Transactional
//    public Page<Order> fetchFiltered(String executed, String orderAgeInDays, PageRequest request) {
//        Date startTime = new Date();
//        if (!"all".equals(orderAgeInDays)) {
//            int days = Integer.parseInt(orderAgeInDays);
//            Calendar c = Calendar.getInstance();
//            c.setTime(new Date());
//            c.add(Calendar.HOUR_OF_DAY, -(days * 24));
//            startTime = c.getTime();
//        }
//        if (!"all".equals(executed) && !"all".equals(orderAgeInDays)) {
//            boolean executedState = Boolean.parseBoolean(executed);
//            return orderDAO.findByExecutedAndDateCreatedGreaterThan(executedState, startTime, request);
//        } else if (!"all".equals(executed)) {
//            boolean executedState = Boolean.parseBoolean(executed);
//            return orderDAO.findByExecuted(executedState, request);
//        } else if (!"all".equals(orderAgeInDays)) {
//            return orderDAO.findByDateCreatedGreaterThan(startTime, request);
//        } else {
//            return orderDAO.findAll(request);
//        }
//    }

    @Transactional
    public Order createUserOrder(String userLogin, int deliveryCost, String cardNumber) {
        SCart cart = scartService.getCartOrCreate(userLogin);
        if (cart.isEmpty())
//            throw new EmptyCartException();

        Order order = createNewOrder(userLogin, cart, deliveryCost);
        Bill bill = createBill(order, cardNumber);
        order.setBill(bill);
        orderDAO.saveAndFlush(order);

        fillOrderItems(cart, order);
        orderDAO.save(order);
        cartService.clearCart(userLogin);

        return order;
    }


    public void updateStatus(long orderId, boolean executed) {
        Order order = orderDAO.findById(orderId).orElse(null);
        if (order != null) {
            order.setExecuted(executed);
            orderDAO.save(order);
        }
    }

    private Order createNewOrder(String userLogin, SCart cart, int deliveryCost) {
        return new Order.Builder()
                .setUserAccount(userAccountService.findByEmail(userLogin))
                .setProductsCost(cart.getItemsCost())
                .setDateCreated(new Date())
                .setExecuted(false)
                .build();
    }

    private Bill createBill(Order order, String cardNumber) {
        return new Bill.Builder()
                .setOrder(order)
                .setNumber(new Random().nextInt(999999999))
                .setTotalCost(order.getProductsCost() + order.getDeliveryCost())
                .setPayed(true)
                .setDateCreated(new Date())
                .setCcNumber(cardNumber)
                .build();
    }

    private void fillOrderItems(Cart cart, Order order) {
        Set<OrderedProduct> ordered = cart.getCartItems().stream()
                .map(item -> createOrderedProduct(order, item))
                .collect(toSet());
        order.setOrderedProducts(ordered);
    }

    private OrderedProduct createOrderedProduct(Order order, CartItem item) {
        return new OrderedProduct.Builder()
                .setProduct(item.getProduct())
                .setOrder(order)
                .setQuantity(item.getQuantity())
                .build();
    }
}
