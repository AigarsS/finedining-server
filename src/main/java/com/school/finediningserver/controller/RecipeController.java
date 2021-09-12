package com.school.finediningserver.controller;

import com.school.finediningserver.dto.RecipeDto;
import com.school.finediningserver.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:8081")
//@CrossOrigin("https://finedining.herokuapp.com")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getRecipes() {
        return recipeService.getAll();
    }

// TODO: Implement possibility to getAllRecipesByUser
//    @GetMapping
//    public List<RecipeDto> getRecipesByUser() {
//        return recipeService.getAllByUser();
//    }

    @GetMapping(value = "/{id}")
    public RecipeDto getRecipe(@PathVariable Long id) {
        return recipeService.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public RecipeDto createRecipe(
            @RequestPart("properties") RecipeDto recipeDto,
            @RequestPart("files") MultipartFile[] files) throws IOException {
        return recipeService.save(recipeDto, files);
    }

// FIXME: edit recipe controller
//    @PutMapping(value = "/{id}")
//    public RecipeDto editRecipe(
//            @PathVariable Long id,
//            @RequestPart("properties") RecipeDto recipeDto,
//            @RequestPart("files") MultipartFile[] files) throws IOException {
//        Recipe recipe = convertToEntity(recipeDto);
//        Recipe recipeCreated = recipeService.save(recipe, files);
//        return convertToDto(recipeCreated);
//    }

    @DeleteMapping(value = "/{id}")
    public RecipeDto deleteRecipe(@PathVariable Long id){
        return recipeService.delete(id);
    }


}
