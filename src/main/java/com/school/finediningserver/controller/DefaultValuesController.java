package com.school.finediningserver.controller;

import com.school.finediningserver.dto.UnitDto;
import com.school.finediningserver.model.ProductCategory;
import com.school.finediningserver.service.ProductCategoryService;
import com.school.finediningserver.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
//@CrossOrigin("https://finedining.herokuapp.com")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/default_values")
public class DefaultValuesController {

    private final UnitService unitService;
    private final ProductCategoryService productCategoryService;

    @GetMapping(value = "/units")
    public List<UnitDto> getUnits() {
        return unitService.getAll();
    }

    @GetMapping(value = "/product_categories")
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getAll();
    }

}
