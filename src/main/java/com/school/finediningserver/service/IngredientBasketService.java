package com.school.finediningserver.service;

import com.school.finediningserver.model.*;
import com.school.finediningserver.repositories.IngredientBasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IngredientBasketService {

    private final IngredientBasketRepository ingredientBasketRepository;
    private final DailyPlanService dailyPlanService;

    public List<IngredientBasket> getByBasket(Basket basket) {
        return ingredientBasketRepository.findByBasket(basket);
    }

    public void save(Basket basket){
        List<DailyPlan> dailyPlans = dailyPlanService.getByBasket(basket);

        List<Ingredient> ingredients = new ArrayList<>();

// TODO: Possible refactoring with streams?
        for (DailyPlan dailyPlan : dailyPlans ) {
            List<Ingredient> dailyPlanIngredients = dailyPlan.getRecipe().getIngredients();
            ingredients = Stream.of(ingredients, dailyPlanIngredients)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

// TODO: Possible refactoring with streams?
        for (Ingredient ingredient: ingredients) {
            IngredientBasket ingredientBasket = new IngredientBasket();
            ingredientBasket.setIngredient(ingredient);
            ingredientBasket.setBasket(basket);

            ingredientBasketRepository.save(ingredientBasket);
        }
    }

    public void delete(IngredientBasket ingredientBasket) {
        ingredientBasketRepository.delete(ingredientBasket);
    }

}
