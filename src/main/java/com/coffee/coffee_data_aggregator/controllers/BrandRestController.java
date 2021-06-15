package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.service.BrandService;
import com.coffee.coffee_data_aggregator.util.CategoryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands/check_unique")
    public String checkUnique(@Param("id") Long id,
                              @Param("name") String name){
        return brandService.checkUnique(id,name);
    }

    @GetMapping("/brands/{id}/categories")
    public List<CategoryDTO> listCategoriesByBrand(@PathVariable(name = "id") Long brandID){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        try {
            Brand brand = brandService.getBrand(brandID);
            Set<ProductCategory> categorySet = brand.getCategory();

            for(ProductCategory category : categorySet){
                CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
                categoryDTOList.add(dto);
            }


        }catch (Exception ex){
            ex.getMessage();
            // TODO Exeption
        }
        return  categoryDTOList;
    }
}
