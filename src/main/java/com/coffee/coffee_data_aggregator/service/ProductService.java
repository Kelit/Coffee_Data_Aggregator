package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.ProductInfo;
import com.coffee.coffee_data_aggregator.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    CategoryService categoryService;

    public ProductInfo findOne(String productId) { return  productInfoRepository.findByProductId(productId); }

//    public Page<ProductInfo> findUpAll(Pageable pageable) {
//        return productInfoRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
//    }

    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAllByOrderByProductId(pageable);
    }

    public Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    @Transactional
    public void increaseStock(String productId, int amount) {
        ProductInfo productInfo = findOne(productId);
//        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = productInfo.getProductStock() + amount;
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Transactional
    public void decreaseStock(String productId, int amount) {
        ProductInfo productInfo = findOne(productId);
//        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = productInfo.getProductStock() - amount;
//        if(update <= 0) throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH );

        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Transactional
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = findOne(productId);
//        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
//
//        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
//            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }

        //update
//        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Transactional
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = findOne(productId);
//        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
//
//        if (productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()) {
//            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }

        //update
//        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    public ProductInfo update(ProductInfo productInfo) throws Exception {

        // if null throw exception
        categoryService.findByCategoryType(productInfo.getCategoryType());
//        if(productInfo.getProductStatus() > 1) {
//            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }


        return productInfoRepository.save(productInfo);
    }

    public ProductInfo save(ProductInfo productInfo) throws Exception {
        return update(productInfo);
    }

    public void delete(String productId) {
        ProductInfo productInfo = findOne(productId);
//        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        productInfoRepository.delete(productInfo);

    }
}
