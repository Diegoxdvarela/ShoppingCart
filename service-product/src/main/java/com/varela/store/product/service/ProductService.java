package com.varela.store.product.service;

import com.varela.store.product.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listAllProducts();

    public Product findById(Long id);
}
