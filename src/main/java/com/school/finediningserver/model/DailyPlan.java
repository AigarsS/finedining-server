package com.school.finediningserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "daily_plans", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "target_date")
    private Date targetDate;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

}
