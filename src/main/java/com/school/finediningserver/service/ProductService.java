package com.school.finediningserver.service;

import com.school.finediningserver.model.Product;
import com.school.finediningserver.model.ProductCategory;
import com.school.finediningserver.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getByName(String name){
        return  productRepository.findByName(name);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product create(String name, ProductCategory productCategory) { return new Product(name, productCategory); };

}
