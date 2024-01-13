package com.brli.myday.repository;

import com.brli.myday.entity.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author brandon
 * 2022-09-10
 */
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Integer> {
  List<ExerciseCategory> findByVisibleTrue();

  Optional<ExerciseCategory> findByVisibleTrueAndId(Integer id);

  List<ExerciseCategory> findByVisibleTrueAndNameContains(String name);
}
