package com.school.finediningserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private Float quantity;

    @ManyToOne
    private Unit unit;

    @ManyToOne
    private Recipe recipe;

    @OneToMany (mappedBy = "ingredient")
    Set<IngredientBasket> ingredientBaskets;

    public Ingredient(Product product, Float quantity, Unit unit, Recipe recipe) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.recipe = recipe;
    }


}
