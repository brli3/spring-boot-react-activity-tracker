package com.brli.myday.controller;

import com.brli.myday.dto.MealCategoryDto;
import com.brli.myday.dto.MealDto;
import com.brli.myday.service.MealService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
@RestController
@RequestMapping("/api/meal")
@SecurityRequirement(name = "bearerAuth")
public class MealController {
  @Autowired
  private MealService mealService;

  @GetMapping("/category/all")
  ResponseEntity<List<MealCategoryDto>> getAllCategories() {
    var categories = mealService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/category/all-visible")
  ResponseEntity<List<MealCategoryDto>> getVisibleCategories() {
    var categories = mealService.getVisibleCategories();
    return ResponseEntity.ok(categories);
  }

  @PostMapping("/category/create")
  ResponseEntity<String> createCategory(@RequestBody MealCategoryDto categoryDto) {
    mealService.createCategory(categoryDto);
    return ResponseEntity.ok("New category created");
  }

  @PutMapping("/category/update")
  ResponseEntity<String> updateCategory(@RequestBody MealCategoryDto categoryDto ) {
    mealService.updateCategory(categoryDto);
    return ResponseEntity.ok("Category updated");
  }

  @DeleteMapping("/category/{id}")
  ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
    mealService.deleteCategory(id);
    return ResponseEntity.ok("Category deleted");
  }

  @GetMapping("/all")
  ResponseEntity<List<MealDto>> getAllMeals() {
    var meals = mealService.getAllMeals();
    return ResponseEntity.ok(meals);
  }

  @GetMapping("/all-visible")
  ResponseEntity<List<MealDto>> getVisibleMeals(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String category ) {
    if (name != null) {
      return ResponseEntity.ok(mealService.getVisibleMeals(name));
    }
    if (category != null) {
      return ResponseEntity.ok(mealService.getVisibleMealsByCategoryName(category));
    }
    return ResponseEntity.ok(mealService.getVisibleMeals());
  }

  @PostMapping("/create")
  ResponseEntity<String> createMeal(@RequestBody MealDto mealDto) {
    mealService.createMeal(mealDto);
    return ResponseEntity.ok("New meal created");
  }

  @PutMapping("/update")
  ResponseEntity<String> updateMeal(@RequestBody MealDto mealDto) {
    mealService.updateMeal(mealDto);
    return ResponseEntity.ok("Meal updated");
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteMeal(@PathVariable Long id) {
    mealService.deleteMeal(id);
    return ResponseEntity.ok("Meal deleted");
  }

  @GetMapping("/all-visible/{id}")
  ResponseEntity<List<MealDto>> getMealsByCategory(@PathVariable Integer id) {
    var meals = mealService.getVisibleMealsByCategory(id);
    return ResponseEntity.ok(meals);
  }

}
