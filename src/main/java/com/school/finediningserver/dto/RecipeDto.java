package com.school.finediningserver.dto;

import com.school.finediningserver.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

    private Long id;
    private String title;
    private String description;
    private Integer cookingTime;
    private Integer usageFrequency;
    private Integer servingsPerRecipe;
    private Image image;
    private UserDto user;
    private List<CookingStepDto> cookingSteps;
    private List<IngredientDto> ingredients;

}
