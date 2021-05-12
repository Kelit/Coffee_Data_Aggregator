package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractRestController<ProductInfo, ProductRepository> {
    @Autowired
    ProductRepository repository;

    public ProductController(ProductRepository repo) {
        super(repo);
    }


    @PostMapping("/save")
    public void saveUser(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        model.setProductIcon(fileName);
        repo.save(model);

        String uploadDir = "product-photos/" + model.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

    }
}
