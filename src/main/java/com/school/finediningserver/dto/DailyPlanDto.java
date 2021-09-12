package com.school.finediningserver.dto;

import lombok.Data;

@Data
public class DailyPlanDto {

    private Long id;
    private String targetDate;
    private RecipeDto recipe;
    private UserDto user;

}
