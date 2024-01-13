package com.brli.myday.controller;

import com.brli.myday.dto.ExerciseCategoryDto;
import com.brli.myday.dto.ExerciseDto;
import com.brli.myday.service.ExerciseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author brandon
 * 2022-09-16
 */
@RestController
@RequestMapping("/api/exercise")
@SecurityRequirement(name = "bearerAuth")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @GetMapping("/category/all")
  ResponseEntity<List<ExerciseCategoryDto>> getAllCategories() {
    var categories = exerciseService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/category/all-visible")
  ResponseEntity<List<ExerciseCategoryDto>> getVisibleCategories() {
    var categories = exerciseService.getVisibleCategories();
    return ResponseEntity.ok(categories);
  }

  @PostMapping("/category/create")
  ResponseEntity<String> createCategory(@RequestBody ExerciseCategoryDto categoryDto) {
    exerciseService.createCategory(categoryDto);
    return ResponseEntity.ok("New category created");
  }

  @PutMapping("/category/update")
  ResponseEntity<String> updateCategory(@RequestBody ExerciseCategoryDto categoryDto ) {
    exerciseService.updateCategory(categoryDto);
    return ResponseEntity.ok("Category updated");
  }

  @DeleteMapping("/category/{id}")
  ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
    exerciseService.deleteCategory(id);
    return ResponseEntity.ok("Category deleted");
  }

  @GetMapping("/all")
  ResponseEntity<List<ExerciseDto>> getAllExercises() {
    var exercises = exerciseService.getAllExercises();
    return ResponseEntity.ok(exercises);
  }

  @GetMapping("/all-visible")
  ResponseEntity<List<ExerciseDto>> getVisibleExercises(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String category ) {
    if (name != null) {
      return ResponseEntity.ok(exerciseService.getVisibleExercises(name));
    }
    if (category != null) {
      return ResponseEntity.ok(exerciseService.getVisibleExercisesByCategoryName(category));
    }
    return ResponseEntity.ok(exerciseService.getVisibleExercises());
  }

  @PostMapping("/create")
  ResponseEntity<String> createExercise(@RequestBody ExerciseDto exerciseDto) {
    exerciseService.createExercise(exerciseDto);
    return ResponseEntity.ok("New exercise created");
  }

  @PutMapping("/update")
  ResponseEntity<String> updateExercise(@RequestBody ExerciseDto exerciseDto) {
    exerciseService.updateExercise(exerciseDto);
    return ResponseEntity.ok("Exercise updated");
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteExercise(@PathVariable Long id) {
    exerciseService.deleteExercise(id);
    return ResponseEntity.ok("Exercise deleted");
  }

  @GetMapping("/all-visible/{id}")
  ResponseEntity<List<ExerciseDto>> getExercisesByCategory(@PathVariable Integer id) {
    var exercises = exerciseService.getVisibleExercisesByCategory(id);
    return ResponseEntity.ok(exercises);
  }

}
