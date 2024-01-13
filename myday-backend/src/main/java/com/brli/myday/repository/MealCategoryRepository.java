package com.brli.myday.repository;

import com.brli.myday.entity.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author brandon
 * 2022-09-23
 */
public interface MealCategoryRepository extends JpaRepository<MealCategory, Long> {
  List<MealCategory> findByVisibleTrue();

  Optional<MealCategory> findByVisibleTrueAndId(Integer id);

  List<MealCategory> findByVisibleTrueAndNameContains(String name);
}
