package com.product.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.entity.Product;
import com.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

	private final ProductRepository repository;
	// @Autowired
	// private ProductService productService;

	public DataInitializer(ProductRepository repository) {
		this.repository = repository;
		// this.productService = productService;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application has started!");
		// Add any custom logic you want to run on startup

		// Only initialize data if repository is empty to avoid update/locking issues
		if (repository.count() > 0) {
			log.info("Products already present - skipping data initialization.");
			return;
		}

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Product>> typeRef = new TypeReference<>() {
		};
		InputStream input = getClass().getResourceAsStream("/data/products.json");
		if (input == null) {
			log.error("products.json not found on classpath, skipping data initialization.");
			return;
		}
		List<Product> product = mapper.readValue(input, typeRef);

		// Ensure we don't accidentally try to update existing rows by clearing ids
		product.forEach(p -> p.setId(null));
		repository.saveAll(product);
		// productService.createProducts(product);
	}
}
