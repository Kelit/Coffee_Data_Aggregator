package com.coffee.coffee_data_aggregator;

import com.coffee.coffee_data_aggregator.model.Product;
import com.coffee.coffee_data_aggregator.model.ProductCategory;
import com.coffee.coffee_data_aggregator.repository.ProductRepository;
import org.hibernate.mapping.PrimaryKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;


    private TestEntityManager entityManager;

    @Test
    public  void testAddOneProduct(){
       ProductCategory category =  entityManager.find(ProductCategory.class, 20);

        Date  now = new Date();
        now.setTime(123);
       Product newProduct = new Product();
       newProduct.setName("1");
       newProduct.setNewProduct(false);
       newProduct.setProductPrice(123.6);
       newProduct.setHotProduct(false);
       newProduct.setOldProductPrice(123.1);
       newProduct.setProductDescription("Vkusno");
       newProduct.setCreateTime(now);
       newProduct.setIcon(null);

        Product  item =  productRepository.save(newProduct);
        assert (item.getId() > 0);
    }

}
