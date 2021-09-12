package com.school.finediningserver.repositories;

import com.school.finediningserver.model.Basket;
import com.school.finediningserver.model.IngredientBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientBasketRepository extends JpaRepository<IngredientBasket, Long> {
    List<IngredientBasket> findByBasket(Basket basket);
}
