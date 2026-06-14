package com.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

public class ProductServiceTest {

    @Test
    void getAllProducts_delegatesToRepository() {
        ProductRepository repo = Mockito.mock(ProductRepository.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());

        ProductService svc = new ProductService();
        // inject private field via reflection for test simplicity
        try {
            java.lang.reflect.Field f = ProductService.class.getDeclaredField("productRepository");
            f.setAccessible(true);
            f.set(svc, repo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(svc.getAllProducts()).isEmpty();
    }

    @Test
    void getProductById_whenNotFound_throws() {
        ProductRepository repo = Mockito.mock(ProductRepository.class);
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ProductService svc = new ProductService();
        try {
            java.lang.reflect.Field f = ProductService.class.getDeclaredField("productRepository");
            f.setAccessible(true);
            f.set(svc, repo);
        } catch (Exception e) { throw new RuntimeException(e); }

        assertThrows(RuntimeException.class, () -> svc.getProductById(99L));
    }
}
