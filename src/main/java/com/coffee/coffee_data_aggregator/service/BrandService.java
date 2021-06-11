package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> listAll(){ return brandRepository.findAll(); }
    public Brand saveBrand(Brand brand){ return  brandRepository.save(brand); }

    public Brand getBrand(Long id){
        return  brandRepository.findById(id).get();
        // TODO Exaption!!
    }

    public void deleteBrand(Long id){
        Long brandbyId = brandRepository.countById(id);
//        if(brandbyId == null || brandbyId == 0){
//            throw
//        }

        brandRepository.deleteById(id);
    }

    public String checkUnique(Long id, String name){
        boolean isCreatingNew = (id == null || id == 0);

        Brand brandByName = brandRepository.findByName(name);

        if(isCreatingNew){
            if(brandByName != null) return "Duplicate";
        }else {
            if(brandByName != null && brandByName.getId() != id) return "Duplicate";
        }

        return "OK";
    }
}
