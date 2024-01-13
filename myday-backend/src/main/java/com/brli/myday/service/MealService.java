package com.brli.myday.service;

import com.brli.myday.dto.MealCategoryDto;
import com.brli.myday.dto.MealDto;

import java.util.List;

/**
 * @author brandon
 * 2022-09-24
 */
public interface MealService {

  void createCategory(MealCategoryDto categoryDTO);

  void updateCategory(MealCategoryDto categoryDTO);

  void deleteCategory(Integer id);

  List<MealCategoryDto> getVisibleCategories();

  List<MealCategoryDto> getAllCategories();

  void createMeal(MealDto mealDto);

  void updateMeal(MealDto mealDto);

  void deleteMeal(Long id);

  List<MealDto> getVisibleMeals();

  List<MealDto> getVisibleMeals(String name);

  List<MealDto> getAllMeals();

  List<MealDto> getVisibleMealsByCategory(Integer id);

  List<MealDto> getVisibleMealsByCategoryName(String name);

}
