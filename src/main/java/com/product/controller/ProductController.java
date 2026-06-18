package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	// Single product
    @PostMapping("/addproduct")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    
    // Multiple products
	@PostMapping("/addproducts")
	public List<Product> createProduct(@RequestBody List<Product> products) {
		return productService.createProducts(products);
	}

	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product productUpdate) {
		return productService.updateProduct(id, productUpdate);
	}

	@DeleteMapping("/product/{name}")
	public String deleteProductByName(@PathVariable String name) {
		productService.deleteProduct(name);
		return "Product data for " + name + " has been deleted and cache evicted.";
	}

	@DeleteMapping("/product/{id}")
	public String deleteProductById(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "Product data for the id " + id + " has been deleted and cache evicted.";
	}
}
