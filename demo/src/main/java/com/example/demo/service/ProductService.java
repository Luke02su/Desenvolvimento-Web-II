package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Product;

public interface ProductService { //Interface comum
    List<Product> getAllProducts(); //Ideal não é acessar repository direto
    void saveProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);
}
