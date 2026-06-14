package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error occured");
		}
	}

	public Product getProductById(Long id) {

		Optional<Product> prod = productRepository.findById(id);
		if (!prod.isPresent()) {
			throw new RuntimeException("Product is not found!");
		}
		return prod.get();
	}

	public Product createProduct(Product product) {
		// return productRepository.save(product);
		return productRepository.findByName(product.getName()).orElseGet(() -> productRepository.save(product));
	}

	public List<Product> createProducts(List<Product> products) {
		// return productRepository.saveAll(products);
		return products.stream()
				.map(p -> productRepository.findByName(p.getName()).orElseGet(() -> productRepository.save(p)))
				.toList();
	}

	public Product updateProduct(Long id, Product productUpdate) {
		return productRepository.findById(id).map(existingProduct -> {
			existingProduct.setName(productUpdate.getName());
			existingProduct.setDescription(productUpdate.getDescription());
			existingProduct.setPrice(productUpdate.getPrice());
			existingProduct.setImageUrl(productUpdate.getImageUrl());
			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
	}

	public void deleteProduct(String name) {
		log.info("Removing product data for name: " + name);
		productRepository.deleteByName(name);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
