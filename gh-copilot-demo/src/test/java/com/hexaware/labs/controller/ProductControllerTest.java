package com.hexaware.labs.controller;

import com.hexaware.labs.model.Product;
import com.hexaware.labs.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

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
    when(productService.getAllProducts()).thenReturn(Arrays.asList(product));
    List<Product> products = productController.getAllProducts();
    assertEquals(1, products.size());
    verify(productService, times(1)).getAllProducts();
  }

  @Test
  void testGetProductById() {
    when(productService.getProductById(1L)).thenReturn(Optional.of(product));
    ResponseEntity<Product> response = productController.getProductById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(product, response.getBody());
  }

  @Test
  void testCreateProduct() {
    when(productService.createProduct(any(Product.class))).thenReturn(product);
    ResponseEntity<Product> response = productController.createProduct(product);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(product, response.getBody());
  }

  @Test
  void testUpdateProduct() {
    when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);
    ResponseEntity<Product> response = productController.updateProduct(1L, product);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(product, response.getBody());
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productService).deleteProduct(1L);
    ResponseEntity<Object> response = productController.deleteProduct(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().toString().contains("Product deleted successfully"));
  }
}
