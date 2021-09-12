package com.school.finediningserver.repositories;

import com.school.finediningserver.model.Basket;
import com.school.finediningserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findByUser(User user);

}
