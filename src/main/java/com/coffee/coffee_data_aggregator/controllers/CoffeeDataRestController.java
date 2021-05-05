package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.model.User;
import com.coffee.coffee_data_aggregator.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/coffee/api")
public class CoffeeDataRestController {

    // Контроллер для работы с продуктами
    @Autowired
    ProductService productService;

    // Показать все
//    @GetMapping("/product")
//    @RequestMapping(value = "/get-product", method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<ProductInfo> getCoffee(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
//        PageRequest request = PageRequest.of(page - 1, size);
//        return productService.findAll(request);
//    }
    @RequestMapping(value = "/get-product", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductInfo> getCoffee(Pageable pageable) {
        return productService.findAll(pageable).getContent();
    }
    // Показать один

    //Удаление товаров
    @RequestMapping(value = "/del-product/{id}/delete", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    //Добавить новый
    @PostMapping("/product/new")
    public ResponseEntity create(@Valid @RequestBody ProductInfo product,
                                 BindingResult bindingResult) throws Exception {
        ProductInfo productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    // Редактировать
    @PutMapping("/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody ProductInfo product,
                               BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }
        return ResponseEntity.ok(productService.update(product));
    }

}
