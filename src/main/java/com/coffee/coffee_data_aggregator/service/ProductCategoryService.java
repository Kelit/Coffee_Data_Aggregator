package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductCategory> listAll(){ return (List<ProductCategory>) categoryRepository.findAll(); }
    public List<ProductCategory> listCategoryForForm(){
        List<ProductCategory> productCategoryListForForm = new ArrayList<>();

        Iterable<ProductCategory> categoryInDB = categoryRepository.findAll();

        for(ProductCategory category: categoryInDB){
            if(category.getParent() == null){

                productCategoryListForForm.add(new ProductCategory(category.getName()));

                Set<ProductCategory> children = category.getChildren();

                for(ProductCategory subcategory : children){
                    productCategoryListForForm.add(new ProductCategory("--" + subcategory.getName()));
                    listCgildren(productCategoryListForForm,subcategory, 1);
                }
            }
        }
        return productCategoryListForForm;
    }

    private void listCgildren(List<ProductCategory> productCategoryListForForm,
                              ProductCategory parent, int sublevel){
            int newSubLevel = sublevel + 1;
            Set<ProductCategory> children = parent.getChildren();
            for(ProductCategory subCategory : children){
                String name = "";
                for(int i = 0; i < newSubLevel; i++){
                    name += "--";
                }
                productCategoryListForForm.add(new ProductCategory(name + subCategory.getName()));
                listCgildren(productCategoryListForForm,subCategory, newSubLevel);
            }
    }
}
