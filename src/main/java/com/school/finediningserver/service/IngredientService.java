package com.school.finediningserver.service;

import com.school.finediningserver.model.Basket;
import com.school.finediningserver.model.Ingredient;
import com.school.finediningserver.model.IngredientBasket;
import com.school.finediningserver.model.Product;
import com.school.finediningserver.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductService productService;
    private final IngredientBasketService ingredientBasketService;

// TODO: group ingredients with same products and convert quantities to SI units
    public List<Ingredient> getByBasket(Basket basket){
        List<IngredientBasket> ingredientBaskets = ingredientBasketService.getByBasket(basket);

        return ingredientBaskets.stream()
                .map(IngredientBasket::getIngredient)
                .collect(Collectors.toList());
    }

    public Ingredient save(Ingredient ingredient){
// TODO: sanitize product names - plural, singular etc. are the same
        String productName = ingredient.getProduct().getName();
        Product product =  productService.getByName(productName);
        if (product == null) {
            product = productService.save(ingredient.getProduct());
        }
        ingredient.setProduct(product);

        return ingredientRepository.save(ingredient);
    }

}
