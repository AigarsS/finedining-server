package com.school.finediningserver.dto;

import com.school.finediningserver.model.ProductCategory;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private ProductCategory productCategory;

}
