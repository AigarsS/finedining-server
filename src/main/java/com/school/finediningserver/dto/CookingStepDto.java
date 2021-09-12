package com.school.finediningserver.dto;

import com.school.finediningserver.model.Image;
import lombok.Data;

@Data
public class CookingStepDto {

    private Integer stepNumber;
    private String description;
    private Image image;

}
