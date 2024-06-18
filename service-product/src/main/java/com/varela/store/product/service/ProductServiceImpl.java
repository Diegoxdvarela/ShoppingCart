package com.varela.store.product.service;

import com.varela.store.product.entity.Product;
import com.varela.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{
    @Autowired
    private RestTemplate restTemplate;

    private final ProductRepository productRepository;
    @Override
    public List<Product> listAllProducts() {
        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products",
                        Product[].class
                );
        Product[] products = response.getBody();
        assert products != null;
        return Arrays.asList(products);
    }

    @Override
    public Product findById(Long id) {
        ResponseEntity<Product> response =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/"+id,
                        Product.class
                );
        Product product = response.getBody();
        assert product != null;
        return product;
    }


}
