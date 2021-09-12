package com.school.finediningserver.repositories;

import com.school.finediningserver.model.CookingStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingStepRepository extends JpaRepository<CookingStep, Long> {
}
