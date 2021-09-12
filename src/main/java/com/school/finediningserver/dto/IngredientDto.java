package com.school.finediningserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private String product;
    private Float quantity;
    private String unit;
    private String category;

}
