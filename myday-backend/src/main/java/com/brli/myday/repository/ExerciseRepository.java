package com.brli.myday.repository;

import com.brli.myday.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author brandon
 * 2022-09-10
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
  List<Exercise> findByVisibleTrue();

  Optional<Exercise> findByVisibleTrueAndId(Long id);

  List<Exercise> findByVisibleTrueAndCategoryId(Integer id);

  List<Exercise> findByVisibleTrueAndNameContains(String name);
}
