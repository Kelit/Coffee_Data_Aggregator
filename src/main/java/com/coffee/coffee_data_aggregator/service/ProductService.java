package com.coffee.coffee_data_aggregator.service;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.lang.model.UnknownEntityException;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    @Transactional
    public Page<Product> findAll(PageRequest request) {
        return productRepository.findAll(request);
    }
    @Transactional
    public Product getProduct(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new UnknownEntityException(Product.class, productId));
    }

    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public void create(Product product, String distilleryTitle) {
        saveInternal(product, distilleryTitle, true);
    }

    @Transactional
    public void update(long productId, Product product, String distilleryTitle) {
        Product original = getProduct(productId);
        product.setId(original.getId());
        saveInternal(product, distilleryTitle, original.isAvailable()); // keep original availability
    }

    private void saveInternal(Product changed, String distilleryTitle, boolean available) {
        productRepository.save(changed);
    }

    public void updateAvailability(Map<Boolean, List<Long>> productIdsByAvailability) {
        for (Map.Entry<Boolean, List<Long>> e : productIdsByAvailability.entrySet()) {
            Boolean targetAvailability = e.getKey();
            List<Product> productsToUpdate = e.getValue().stream()
                    .map(this::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(product -> product.isAvailable() != targetAvailability)
                    .collect(Collectors.toList());
            for (Product product : productsToUpdate) {
                product.setAvailable(targetAvailability);
                productRepository.save(product);
            }
        }
    }

    @Transactional
    public void delete(long product) {
        productRepository.deleteById(product);
    }
}
