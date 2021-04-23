package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    public List<ProductInfo> findAllProduct(){ return  productInfoRepository.findAll();}

    //delete product

    //add product
    @Transactional
    public boolean addProduct(ProductInfo pi){
        if(pi == null) return false;
        pi.setProductName(pi.getProductName());
        pi.setProductPrice(pi.getProductPrice());
        pi.setProductStock(pi.getProductStock());
        pi.setProductDescription(pi.getProductDescription());
        pi.setProductIcon(pi.getProductIcon());

        productInfoRepository.save(pi);
        System.out.println("продукт сохранен сохранен - " + pi.getProductId() + ":" + pi.getProductName());
        return true;
    }
    //save


}
