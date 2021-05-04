package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll(){
        List<ProductCategory> res = productCategoryRepository.findAllByOrderByCategoryType();
        return res;
    }

    public ProductCategory findByCategoryType(Integer categoryType) throws Exception {
        ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new Exception();
        return res;
    }

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> res = productCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }


    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
