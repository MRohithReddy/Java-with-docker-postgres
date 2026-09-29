package com.learning.dockpost.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.learning.dockpost.demo.model.Product;
import com.learning.dockpost.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductControllerIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCrudProducts() {
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Gaming laptop");
        product.setPrice(1299.99);

        Product saved = productRepository.save(product);
        assertThat(saved.getId()).isNotNull();

        Product found = productRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Laptop");

        found.setName("Updated Laptop");
        found.setPrice(1399.99);
        Product updated = productRepository.save(found);
        assertThat(updated.getName()).isEqualTo("Updated Laptop");

        productRepository.deleteById(updated.getId());
        assertThat(productRepository.findById(updated.getId())).isEmpty();
    }
}
