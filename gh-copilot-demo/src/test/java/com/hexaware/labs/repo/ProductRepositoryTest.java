package com.hexaware.labs.repo;

import com.hexaware.labs.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
  @Autowired
  private ProductRepository productRepository;

  private Product product;

  @BeforeEach
  void setUp() {
    product = new Product();
    product.setName("Test Product");
    product.setDescription("Test Description");
    product.setPrice(100.0);
    product = productRepository.save(product);
  }

  @Test
  void testFindAll() {
    List<Product> products = productRepository.findAll();
    assertFalse(products.isEmpty());
  }

  @Test
  void testFindById() {
    Optional<Product> found = productRepository.findById(product.getId());
    assertTrue(found.isPresent());
    assertEquals(product.getName(), found.get().getName());
  }

  @Test
  void testSave() {
    Product newProduct = new Product();
    newProduct.setName("Another Product");
    newProduct.setDescription("Another Description");
    newProduct.setPrice(200.0);
    Product saved = productRepository.save(newProduct);
    assertNotNull(saved.getId());
  }

  @Test
  void testDeleteById() {
    productRepository.deleteById(product.getId());
    Optional<Product> deleted = productRepository.findById(product.getId());
    assertFalse(deleted.isPresent());
  }
}
