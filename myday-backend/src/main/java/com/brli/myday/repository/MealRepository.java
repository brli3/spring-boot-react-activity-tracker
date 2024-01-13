package com.brli.myday.repository;

import com.brli.myday.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author brandon
 * 2022-09-23
 */
public interface MealRepository extends JpaRepository<Meal, Long> {
  List<Meal> findByVisibleTrue();

  Optional<Meal> findByVisibleTrueAndId(Long id);

  List<Meal> findByVisibleTrueAndCategoryId(Integer id);

  List<Meal> findByVisibleTrueAndNameContains(String name);
}
