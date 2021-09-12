package com.school.finediningserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredients_baskets", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    Basket basket;

}
