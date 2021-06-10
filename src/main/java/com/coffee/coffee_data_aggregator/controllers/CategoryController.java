package com.coffee.coffee_data_aggregator.controllers;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.CategoryRepository;
import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class  CategoryController{
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public String listAll(Model model){
        List<ProductCategory> listCategory = productCategoryService.listAll();
        model.addAttribute("listCategories", listCategory);

        return "categories/categories";
    }

    @GetMapping("/categories/new")
    public String newCategory(Model model){
        List<ProductCategory> listCategoryForForm = productCategoryService.listCategoryForForm();

        model.addAttribute("category", new ProductCategory());
        model.addAttribute("listCategories", listCategoryForForm);
//        model.addAttribute("listCategories", listCategory);

        return "categories/category_form";
    }

    @PostMapping("/category/save")
    public String saveCategory(ProductCategory category,
                                @RequestParam("fileIcon")MultipartFile multipartFile){
        // TODO Save file
        productCategoryService.saveCategory(category);
         return "save file";
//        return "redirect/:category";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable(name = "id") Long id,
                               Model model
//                               RedirectAttributes re
                                ){
        ProductCategory category = productCategoryService.getCategory(id);
        List<ProductCategory> categoryList = productCategoryService.listCategoryForForm();

        //TODO EDIT FORM!!
//        model.addAttribute("category", category);
//        model.addAttribute("listCategories", categoryList);
//        model.addAttribute("Edit category", "Page Edit Category" + id);
//        return "categories/category_form"
        return "category";
    }

    @GetMapping("/categories/{id}/enabled/{status}")
    public String updateActive(@PathVariable("id") Long id,
                                @PathVariable("status")boolean active
//                               RedirectAttributes re
                               ){
        productCategoryService.updateCategoryActiveStatus(id, active);
        String msg = "The category ID " + id + "has been" + (active ? "true" : "false");
//        re.addFlashAttribute("messagw", msg);

        return  "redirect:/categories";
    }

    @GetMapping("categories/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id,
                                 Model model,
                                 RedirectAttributes re
                                 ){
        productCategoryService.delete(id);
//        re.addFlashAttribute("message","Category " + id + "deleted successfull");
        return "redirect:/categories";
    }

}
