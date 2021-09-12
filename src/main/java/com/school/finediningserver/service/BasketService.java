package com.school.finediningserver.service;

import com.school.finediningserver.dto.BasketDto;
import com.school.finediningserver.dto.IngredientDto;
import com.school.finediningserver.model.*;
import com.school.finediningserver.repositories.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final IngredientBasketService ingredientBasketService;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final ModelMapper modelMapper;

// FIXME: convert all ingredients for the basket to DTO
    public List<BasketDto> getAllByUser(User user) {
        List<Basket> baskets = basketRepository.findByUser(user);

        return baskets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public BasketDto get(Long id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        List<Ingredient> ingredients = ingredientService.getByBasket(basketOptional.get());

        return convertToDto(basketOptional.get(), ingredients);
    }

    public BasketDto save(BasketDto basketDto) throws ParseException {
        Basket basket = convertToEntity(basketDto);
        Basket basketCreated = basketRepository.save(basket);
        ingredientBasketService.save(basketCreated);

        return convertToDto(basketCreated);
    }

    public BasketDto delete(Long id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        List<IngredientBasket> ingredientBaskets = ingredientBasketService.getByBasket(basketOptional.get());
        ingredientBaskets.forEach(ingredientBasket -> ingredientBasketService.delete(ingredientBasket));
        basketRepository.delete(basketOptional.get());

        return convertToDto(basketOptional.get());
    }

    private BasketDto convertToDto(Basket basket) {
        BasketDto basketDto = modelMapper.map(basket, BasketDto.class);

        return basketDto;
    }

    private BasketDto convertToDto(Basket basket, List<Ingredient> ingredients) {
        BasketDto basketDto = modelMapper.map(basket, BasketDto.class);
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for (Ingredient ingredient: ingredients) {
            IngredientDto ingredientDto = modelMapper.map(ingredient, IngredientDto.class);
            ingredientDtos.add(ingredientDto);
        }
        basketDto.setIngredients(ingredientDtos);

        return basketDto;
    }

    private Basket convertToEntity(BasketDto basketDto) throws ParseException {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Basket basket = modelMapper.map(basketDto, Basket.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(basketDto.getStartDate());
        Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(basketDto.getEndDate());
        basket.setStartDate(startDate);
        basket.setEndDate(endDate);
        basket.setUser(user);
        return basket;
    }
}
