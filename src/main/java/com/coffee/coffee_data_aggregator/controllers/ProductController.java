package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.ProductImage;
import com.coffee.coffee_data_aggregator.service.BrandService;
import com.coffee.coffee_data_aggregator.service.ProductService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            RedirectAttributes re,
            @RequestParam("fileImage")MultipartFile mainImageFile,
            @RequestParam("fileImage")MultipartFile[] multiImageMultipart,
            @RequestParam(name = "detailIDs", required = false) String[] detailIDs,
            @RequestParam(name = "detailNames", required = false) String[] detailNames,
            @RequestParam(name = "detailValues", required = false) String[] detailValues,
            @RequestParam(name = "imageIDs", required = false) String[] imageIDs,
            @RequestParam(name = "imageNames", required = false) String[] imageNames
            ){

        setMainIcon(mainImageFile, product);
        setExistExtraImageNames(imageIDs, imageNames, product);
        setExtImage(multiImageMultipart, product);

        setProductDetail(detailIDs, detailNames, detailValues, product);

        deleteExtraImagesWeredRemoedOnForm(product);

        productService.saveProduct(product);

        re.addFlashAttribute("message", "Продукт успешно сохранен");

        return "redirect:products";
    }

    private void deleteExtraImagesWeredRemoedOnForm(Product product) {
        // TODO Delet icon base64
    }

    private void setExistExtraImageNames(
                String[] imageIDs,
                String[] imageNames,
                Product product
    ){
      if(imageIDs == null || imageIDs.length == 0) return;

      Set<ProductImage> images = new HashSet<>();

      for(int count = 0; count < imageIDs.length; count++){
          Long id = Long.getLong(String.valueOf(imageIDs[count]));
          String name = imageNames[count];

          images.add(new ProductImage(id,name,product));
      }


    }

    private void setProductDetail(
            String[] detailIDs,
            String[] detailNames,
            String[] detailValues,
            Product product
    ){
        if(detailNames == null || detailNames.length == 0) return;
        for( int c = 0; c < detailNames.length; c++){
            String name = detailNames[c];
            String value = detailValues[c];
            Long id = Long.getLong(String.valueOf(detailIDs[c]));

            if(id != 0){
                product.addDetail(id, name, value);
            }else if(!name.isEmpty() && !value.isEmpty()){
                 product.addDetail(name, value);
            }
        }
    }

    private void setExtImage(MultipartFile[] multiImageMultipart,Product product){
        if(multiImageMultipart.length > 0){
            for(MultipartFile multipartFile : multiImageMultipart){
                product.addExtraImage(multipartFile.getContentType()); // TODO Content
            }
        }
    }

    private void setMainIcon(MultipartFile mainImageFile, Product product){
        if(!mainImageFile.isEmpty()){
            product.setMainIcon(mainImageFile.getContentType());
        }else{
            product.setMainIcon("");// TODO!! set BASE64
        }
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


    @GetMapping("/products/edit/{id}")
    public String editProduct(
            @PathVariable("id") Long id,
            Model model,
            RedirectAttributes re
    ){
        try{
            Product product = productService.getProduct(id);
            List<Brand> brandList = brandService.listAll();

            model.addAttribute("product", product);
            model.addAttribute("brandList", brandList);
            model.addAttribute("pageTitle", "Edit Product");

            //TODO !! Get return Icon
//            Integer numberOfExistingExtraImages = productService.getImages().size();

            return "/products/product_form";
        }catch (Exception ex){
            re.addFlashAttribute("message",ex.getMessage());
            return "redirect:/products";
        }

    }
}
