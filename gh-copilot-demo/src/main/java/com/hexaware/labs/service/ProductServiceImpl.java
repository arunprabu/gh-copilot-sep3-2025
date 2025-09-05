package com.hexaware.labs.service;

import com.hexaware.labs.model.Product;
import com.hexaware.labs.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.hexaware.labs.controller.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    return productRepository.findById(id)
        .map(existingProduct -> {
          existingProduct.setName(product.getName());
          existingProduct.setDescription(product.getDescription());
          existingProduct.setPrice(product.getPrice());
          return productRepository.save(existingProduct);
        })
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
  }

  @Override
  public void deleteProduct(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product not found with id: " + id);
    }
    productRepository.deleteById(id);
  }
}
