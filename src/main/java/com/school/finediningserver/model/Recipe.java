package com.school.finediningserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "usage_frequency")
    private Integer usageFrequency;

    @Column(name = "servings_per_recipe")
    private Integer servingsPerRecipe;

    @OneToOne(orphanRemoval = true)
    private Image image;

    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "recipe", orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OrderBy("stepNumber ASC")
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CookingStep> cookingSteps = new ArrayList<>();

}
