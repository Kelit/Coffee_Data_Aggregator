package com.coffee.coffee_data_aggregator.controllers;

import com.coffee.coffee_data_aggregator.model.Brand;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.service.BrandService;
import com.coffee.coffee_data_aggregator.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BrandsController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/brands")
    public String listAll(Model model){
        List<Brand> listBrands = brandService.listAll();
        model.addAttribute("listBrands", listBrands);

        return "brands/brands";
    }

    @GetMapping("/brands/new")
    public String newBrand(Model model){
        List<ProductCategory> listCategories = productCategoryService.listCategoryForForm();

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", new Brand());
        model.addAttribute("Title", "New Brand");

        return "brands/brand_form";
    }

    @PostMapping("/brands/save")
    public String saveBrand(Brand brand,
                            @RequestParam("fileIcon")MultipartFile multipartFile,
                            RedirectAttributes re
                            ){
        if(!multipartFile.isEmpty()){
            //TODO NotEmpty
//            brand.setIcon();
            brandService.saveBrand(brand);

        }else brandService.saveBrand(brand);

        re.addFlashAttribute("message", "Brand Save");
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String editBrand(@PathVariable(name = "id") Long id,
                            Model model,
                            RedirectAttributes re
                            ){
        // TODO Exeption
        Brand brand = brandService.getBrand(id);
        List<ProductCategory> productCategoryList = productCategoryService.listCategoryForForm();

        model.addAttribute("brand",brand);
        model.addAttribute("listCategories", productCategoryList);
        model.addAttribute("Edit Page", "Edit band " + id);

        return "brands/brand_form";
    }
    @GetMapping("/brands/delete/{id}")
    public String deletBrand(@PathVariable(name = "id") Long id,
                            RedirectAttributes re
                            ){
        // TODO Exeption
        brandService.deleteBrand(id);

        re.addFlashAttribute("message", "Brand delete");
        return "brands/brand_form";
    }


}
