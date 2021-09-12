package com.school.finediningserver.repositories;

import com.school.finediningserver.model.DailyPlan;
import com.school.finediningserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    List<DailyPlan> findByUser(User user);

    List<DailyPlan> findByTargetDateBetween(Date startDate, Date endDate);

}
