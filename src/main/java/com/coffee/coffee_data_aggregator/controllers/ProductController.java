package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import com.coffee.coffee_data_aggregator.service.BrandService;
import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import com.coffee.coffee_data_aggregator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController{
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/products")
    public String listAll(Model model){
        List<Product> productList = productService.listAll();

        model.addAttribute("listProducts", productList);

        return "products/products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model){
        List<Brand> listBrands = brandService.listAll();

        Product product = new Product();
        product.setActive(true);
        product.setInStock(true);

        model.addAttribute("product", product);
        model.addAttribute("listBrands", listBrands);
        model.addAttribute("pageTitle","Создать новый продукт");

        return "products/products_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(
            Product product,
            RedirectAttributes re
            ){
        productService.saveProduct(product);
        re.addFlashAttribute("message", "Продукт успешно сохранен");
        return "redirect:products";
    }

    @GetMapping("/products/{id}/enabled/{status}")
    public String updateCategory(
            @PathVariable("id") Long id,
            @PathVariable("status") boolean active,
            RedirectAttributes re
    ){
        productService.updateProductActiveStatus(id, active);
        String status = active ? "true":"false";
        String msg = "Продукту "+ id + " присвоин статус " + active;
        re.addFlashAttribute("message",msg);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(
        @PathVariable(name = "id") Long id,
        Model model,
        RedirectAttributes re
    ){
        try
        {
            productService.deleteProduct(id);
            re.addFlashAttribute("message", "Продукт с : " + id +" удален");
        }catch(Exception ex //TODO Custom exeption
              ){
            re.addFlashAttribute("message", "Ошибка");
        }

        return "redirect:/products";
    }

}
