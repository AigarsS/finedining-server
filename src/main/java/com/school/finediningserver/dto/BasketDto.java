package com.school.finediningserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class BasketDto {

    private Long id;
    private String startDate;
    private String endDate;
    private UserDto user;
    private List<IngredientDto> ingredients;

}
