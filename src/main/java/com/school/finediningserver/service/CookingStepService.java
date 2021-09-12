package com.school.finediningserver.service;

import com.school.finediningserver.model.CookingStep;
import com.school.finediningserver.repositories.CookingStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookingStepService {

    private final CookingStepRepository cookingStepRepository;

    public CookingStep save(CookingStep cookingStep){
        return cookingStepRepository.save(cookingStep);
    }

}
