package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.OrderMain;
import com.coffee.coffee_data_aggregator.model.ProductInOrder;
import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.OrderMainRepository;
import com.coffee.coffee_data_aggregator.repository.ProductInOrderRepository;
import com.coffee.coffee_data_aggregator.repository.ProductInfoRepository;
import com.coffee.coffee_data_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderMainRepository orderMainRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Autowired
    ProductService productService;

    public Page<OrderMain> findAll(Pageable pageable){ return orderMainRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);}

    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderMainRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderMainRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone,pageable);
    }

    public OrderMain findOne(Long orderId) throws Exception {
        OrderMain orderMain = orderMainRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new Exception();
        }
        return orderMain;
    }

    @Transactional
    public OrderMain finish(Long orderId) throws Exception {
        OrderMain orderMain = findOne(orderId);
//        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
//        }

//        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMainRepository.save(orderMain);
        return orderMainRepository.findByOrderId(orderId);
    }

    @Transactional
    public OrderMain cancel(Long orderId) throws Exception {
        OrderMain orderMain = findOne(orderId);
//        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
//        }

//        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderMainRepository.save(orderMain);

        // Restore Stock
        Iterable<ProductInOrder> products = orderMain.getProducts();
        for(ProductInOrder productInOrder : products) {
            ProductInfo productInfo = productInfoRepository.findByProductId(productInOrder.getProductId());
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderMainRepository.findByOrderId(orderId);

    }
}
