package com.school.finediningserver.service;

import com.school.finediningserver.model.ProductCategory;
import com.school.finediningserver.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAll() {

        return productCategoryRepository.findAll();
    }

    public ProductCategory getByName(String name){
        return  productCategoryRepository.findByName(name);
    }

}
