package com.school.finediningserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cooking_steps", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookingStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;

    @ManyToOne
    private Recipe recipe;
}
