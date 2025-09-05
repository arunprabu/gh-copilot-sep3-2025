package com.hexaware.labs.service;

import com.hexaware.labs.model.Product;
import com.hexaware.labs.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  private Product product;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    product = new Product();
    product.setId(1L);
    product.setName("Test Product");
    product.setDescription("Test Description");
    product.setPrice(100.0);
  }

  @Test
  void testGetAllProducts() {
    when(productRepository.findAll()).thenReturn(Arrays.asList(product));
    List<Product> products = productService.getAllProducts();
    assertEquals(1, products.size());
    verify(productRepository, times(1)).findAll();
  }

  @Test
  void testGetProductById() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    Optional<Product> result = productService.getProductById(1L);
    assertTrue(result.isPresent());
    assertEquals(product, result.get());
  }

  @Test
  void testCreateProduct() {
    when(productRepository.save(any(Product.class))).thenReturn(product);
    Product created = productService.createProduct(product);
    assertEquals(product, created);
  }

  @Test
  void testUpdateProduct() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    when(productRepository.save(any(Product.class))).thenReturn(product);
    Product updated = productService.updateProduct(1L, product);
    assertEquals(product, updated);
  }

  @Test
  void testDeleteProduct() {
    when(productRepository.existsById(1L)).thenReturn(true);
    doNothing().when(productRepository).deleteById(1L);
    assertDoesNotThrow(() -> productService.deleteProduct(1L));
    verify(productRepository, times(1)).deleteById(1L);
  }
}
