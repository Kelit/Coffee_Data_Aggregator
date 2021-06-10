package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductCategory getCategory(Long id){
        return categoryRepository.findById(id).get();
    }

    public void saveCategory(ProductCategory category){
        categoryRepository.save(category);
    }

    public void delete(Long id){
        Long countById = categoryRepository.countById(id);
        //TODO Exeptions
//        if(countById == null || countById == 0){
//            throw new
//        }
        categoryRepository.deleteById(id);
    }

    public String checkUnique(Long id, String name, String alias){
        boolean isNew = (id == null || id == 0 );

        ProductCategory productCategoryByName = categoryRepository.findByName(name);
        if(isNew){
            if(productCategoryByName != null){
                return "Duplicate";
            }else {
                ProductCategory categoryByAlias = categoryRepository.findByAlias(alias);
                if(categoryByAlias != null){
                    return "DuplicateAlias";
                }
            }
        }else {
            if(productCategoryByName != null && productCategoryByName.getId() != id)
                return  "DuplicateName";
            ProductCategory categoryByAlias = categoryRepository.findByAlias(alias);
            if(categoryByAlias != null && categoryByAlias.getId() != id)
                return "DuplicateAlias";

        }

        return "OK";
    }

    public void listSubHierarchicalCategories(List<ProductCategory> hierarchicalCatgories,
                                              ProductCategory parent,
                                              int subLevel){
        Set<ProductCategory> children = sortedSubCategories(parent.getChildren());
        int newSubLevel = subLevel + 1;
        for(ProductCategory subCategory : children){
            String msg = "";
            for(int i = 0; i < newSubLevel; i++){
                msg += "--";
            }
            msg += subCategory.getName();

            hierarchicalCatgories.add(ProductCategory.copyFull(subCategory,msg));

            listSubHierarchicalCategories(hierarchicalCatgories, parent, newSubLevel);
        }
    }

    public List<ProductCategory> listAll(){
        List<ProductCategory> rootCategories = categoryRepository.findRootCategory(Sort.by("name").ascending());
        return listHierarchicalCatgories(rootCategories);
    }

    private List<ProductCategory> listHierarchicalCatgories(List<ProductCategory> rootCategories){
        List<ProductCategory> hierarchicalCatgories = new ArrayList<>();

        for(ProductCategory rootCategory: rootCategories){
            //Alert!!
            hierarchicalCatgories.add(ProductCategory.copyFull(rootCategory));

            Set<ProductCategory> children = sortedSubCategories(rootCategory.getChildren());

            for(ProductCategory subCategory : children){
                String msg = "--" + subCategory.getName();
                hierarchicalCatgories.add(ProductCategory.copyFull(subCategory,msg));

                listSubHierarchicalCategories(hierarchicalCatgories, subCategory, 1);
            }
        }

        return hierarchicalCatgories;
    }

    public List<ProductCategory> listCategoryForForm(){
        List<ProductCategory> productCategoryListForForm = new ArrayList<>();

        Iterable<ProductCategory> categoryInDB = categoryRepository.findRootCategory(Sort.by("name").ascending());

        for(ProductCategory category: categoryInDB){
            if(category.getParent() == null){

                productCategoryListForForm.add(ProductCategory.copyIdAndName(category));

                Set<ProductCategory> children = sortedSubCategories(category.getChildren());

                for(ProductCategory subcategory : children){
                    productCategoryListForForm.add(ProductCategory.copyIdAndName(subcategory.getId(),"--" + subcategory.getName()));
                    listSubCategoriesInForm(productCategoryListForForm,subcategory, 1);
                }
            }
        }
        return productCategoryListForForm;
    }

    private void listSubCategoriesInForm(List<ProductCategory> productCategoryListForForm,
                                         ProductCategory parent, int sublevel){
            int newSubLevel = sublevel + 1;
            Set<ProductCategory> children = sortedSubCategories(parent.getChildren());
            for(ProductCategory subCategory : children){
                String name = "";
                for(int i = 0; i < newSubLevel; i++){
                    name += "--";
                }
                name += subCategory.getName();

                productCategoryListForForm.add(ProductCategory.copyIdAndName(subCategory.getId(),name));
                listSubCategoriesInForm(productCategoryListForForm,subCategory, newSubLevel);
            }
    }

    private SortedSet<ProductCategory> sortedSubCategories(Set<ProductCategory> children){
        SortedSet<ProductCategory> sortedSubChildren = new TreeSet<>(new Comparator<ProductCategory>() {
            @Override
            public int compare(ProductCategory cat1, ProductCategory cat2) {
                return cat1.getName().compareTo(cat2.getName());
            }
        });
        sortedSubChildren.addAll(children);

        return sortedSubChildren;
    }

    public void updateCategoryActiveStatus(Long id, boolean active){
        categoryRepository.updateActiveStatus(id, active);
    }
}
