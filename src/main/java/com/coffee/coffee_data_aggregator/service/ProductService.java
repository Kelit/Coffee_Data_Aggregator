package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll(){ return  productRepository.findAll(); }

    public void saveProduct(Product product){
        if(product.getId() == null)
            product.setCreatedTime(new Date());
        if(product.getAlias() == null || product.getAlias().isEmpty()){
            String defaultAlias = product.getName().replaceAll(" ","-");
            product.setAlias(defaultAlias);
        }else
            product.setAlias(product.getAlias().replaceAll(" ","-"));

        product.setUpdatedTime(new Date());

        productRepository.save(product);
    }

    public Product getProduct(Long id){
//        try {
//            return  productRepository.findById(id).get();
//        }catch (NoSuchElementException ex){
//            System.out.println(ex.getMessage()); // TODO Exeption custom
//        }
        return  productRepository.findById(id).get();
    }


    public String checkUnique(Long id, String name){
        boolean isCreateingNew = (id == null || id == 0);
        Product productByName = productRepository.findByName(name);

        if(isCreateingNew){
            if(productByName != null) return  "Дублирование";
        }else {
            if(productByName != null && productByName.getId() != id) return "Дублирование";
        }
        return "OK";
    }

    public void updateProductActiveStatus(Long id, boolean active){
        productRepository.updateActiveStatus(id, active);
    }

    public void deleteProduct(Long id){
        Long countById = productRepository.countById(id);

        if(countById == null || countById == 0){
            // todo Exeptions
        }

        productRepository.deleteById(id);
    }
}
