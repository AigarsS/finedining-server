package com.school.finediningserver.service;

import com.school.finediningserver.dto.IngredientDto;
import com.school.finediningserver.dto.RecipeDto;
import com.school.finediningserver.model.*;
import com.school.finediningserver.repositories.ProductCategoryRepository;
import com.school.finediningserver.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final IngredientService ingredientService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final UnitService unitService;
    private final CookingStepService cookingStepService;
    private final ImageService imageService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<RecipeDto> getAll() {
        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public  RecipeDto get(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        return convertToDto(recipeOptional.get());
    }

    public RecipeDto save(RecipeDto recipeDto, MultipartFile[] files) throws IOException {
        Recipe recipe = convertToEntity(recipeDto);
        Image image = imageService.store(files[0]);
        recipe.setImage(image);
        Recipe recipeCreated = recipeRepository.save(recipe);
        saveIngredients(recipe, recipeCreated);
        saveCookingSteps(recipe, recipeCreated, files);

        return convertToDto(recipeCreated);
    }

// FIXME: Recipe edit functionality
//    public RecipeDto edit(Long id, RecipeDto recipeDto, MultipartFile[] files) throws IOException {
//        Recipe recipe = convertToEntity(recipeDto);
//        Image image = imageService.store(files[0]);
//        recipe.setId(id);
//        recipe.setImage(image);
//        Recipe recipeCreated = recipeRepository.save(recipe);
//        return convertToDto(recipeCreated);
//    }

    public RecipeDto delete(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        recipeRepository.delete(recipeOptional.get());

        return convertToDto(recipeOptional.get());
    }

    private RecipeDto convertToDto(Recipe recipe) {
        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
        List<IngredientDto> ingredientDtos = convertIngredientsToDto(recipe.getIngredients());
        recipeDto.setIngredients(ingredientDtos);

        return recipeDto;
    }

    private Recipe convertToEntity(RecipeDto recipeDto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Recipe recipe = modelMapper.map(recipeDto, Recipe.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        setIngredientsFromDto(recipeDto.getIngredients(), recipe);
        recipe.setUser(user);

        return recipe;
    }

    private void setIngredientsFromDto(List<IngredientDto> ingredientsDto, Recipe recipe) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientsDto.forEach(ingredientDto -> {
            String productName = ingredientDto.getProduct();
            String unitDenotation = ingredientDto.getUnit();
            Float quantity = ingredientDto.getQuantity();

            Product product = productService.getByName(productName);
            if (product == null) {
                String category = ingredientDto.getCategory();
                ProductCategory productCategory = productCategoryService.getByName(category);
                product = productService.save(productService.create(productName, productCategory));
            }
            Unit unit = unitService.getByDenotation(unitDenotation);
            Ingredient ingredient = new Ingredient(product, quantity, unit, recipe);
            ingredients.add(ingredient);
        });
        recipe.setIngredients(ingredients);
    }

    private List<IngredientDto> convertIngredientsToDto(List<Ingredient> ingredients) {
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        ingredients.forEach(ingredient -> {
            String product = ingredient.getProduct().getName();
            String unit = ingredient.getUnit().getDenotation();
            String category = ingredient.getProduct().getProductCategory().getName();

            IngredientDto ingredientDto = new IngredientDto(product, ingredient.getQuantity(), unit, category);
            ingredientDtos.add(ingredientDto);
        });
        return ingredientDtos;
    }

    private void saveIngredients(Recipe recipe, Recipe recipeCreated){
        List<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients != null) {
            ingredients.forEach(ingredient -> {
                ingredient.setRecipe(recipeCreated);
                ingredientService.save(ingredient);
            });
        }
    }

    private void saveCookingSteps(Recipe recipe, Recipe recipeCreated, MultipartFile[] files){
        List<CookingStep> cookingSteps = recipe.getCookingSteps();
        if (cookingSteps != null) {
            AtomicInteger index=new AtomicInteger(0);

            for (CookingStep cookingStep: recipe.getCookingSteps()) {

            }
            recipe.getCookingSteps().forEach(cookingStep -> {
                cookingStep.setRecipe(recipeCreated);
                if (cookingStep.getImage().getName() != null) {
                    try {
                        Image stepImage = imageService.store(files[index.incrementAndGet()]);
                        cookingStep.setImage(stepImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                cookingStepService.save(cookingStep);

            });
        }
    }

}
