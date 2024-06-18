package com.varela.store.product.controller;

import com.varela.store.product.entity.Product;
import com.varela.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity <List<Product>> listProducts(){
        List<Product> listProd = productService.listAllProducts();
        if(listProd.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(listProd);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        if(product!=null){
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
