package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ImageModel;
import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ImageRepository;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductStorageService {
    @Autowired
    private ProductRepository productInfoRepo;

    public ProductInfo store(ProductInfo pI) throws Exception{
        if(pI == null) return null;
        return productInfoRepo.save(pI);
    }

    public Optional<ProductInfo> getFile(Long id) {
        return productInfoRepo.findById(id);
    }

    public Stream<ProductInfo> getAllFiles() {
        return productInfoRepo.findAll().stream();
    }
}
