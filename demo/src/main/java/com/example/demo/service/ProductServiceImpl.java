package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Indica que a classe implementa lógica de serviço (camada de negócio)
public class ProductServiceImpl implements ProductService {
    @Autowired //Injeção automática de dependências (neste caso, o repositório)
    private ProductRepository productRepository;

    @Override //sobreposição
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    
    @Override
    public Product getProductById(long id) {
        Optional<Product> optional = productRepository.findById(id); //Optional Evita NullPointerException
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Produto não encontrado para o id " + id);
        }
        return product;
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}

//Separação de responsabilidades (boas práticas de arquitetura)