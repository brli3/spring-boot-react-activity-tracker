package com.brli.myday.service.impl;

import com.brli.myday.dto.MealCategoryDto;
import com.brli.myday.dto.MealDto;
import com.brli.myday.entity.MealCategory;
import com.brli.myday.entity.Meal;
import com.brli.myday.exception.ResourceInUseException;
import com.brli.myday.exception.ResourceNotFoundException;
import com.brli.myday.repository.MealCategoryRepository;
import com.brli.myday.repository.MealRepository;
import com.brli.myday.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brandon
 * 2022-09-24
 */
@SuppressWarnings("DuplicatedCode")
@Service
public class MealServiceImpl implements MealService {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  MealRepository mealRepository;

  @Autowired
  MealCategoryRepository categoryRepository;

  private MealCategory getCategoryFromDTO(MealCategoryDto categoryDTO) {
    return modelMapper.map(categoryDTO, MealCategory.class);
  }

  private MealCategoryDto getCategoryDTO(MealCategory category) {
    return modelMapper.map(category, MealCategoryDto.class);
  }

  private Meal getMealFromDTO(MealDto mealDTO) {
    var categoryId = mealDTO.getCategoryId();
    categoryRepository.findByVisibleTrueAndId(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category ID not found"));
    var avatar = mealDTO.getAvatar();
    if (avatar == null || "".equals(avatar.trim())) {
      mealDTO.setAvatar("https://static.thenounproject.com/png/551389-200.png");
    }
    return modelMapper.map(mealDTO, Meal.class);
  }

  private MealDto getMealDTO(Meal meal) {
    var mealDto = modelMapper.map(meal, MealDto.class);
    var categoryId = meal.getCategoryId();
    if (categoryId != null && meal.isVisible()) {
      var categoryName = categoryRepository
              .findByVisibleTrueAndId(categoryId)
              .map(MealCategory::getName)
              .orElse(null);
      mealDto.setCategory(categoryName);
    }
    return mealDto;
  }

  @Override
  public void createCategory(MealCategoryDto categoryDTO) {
    var category = modelMapper.map(categoryDTO, MealCategory.class);
    categoryRepository.save(category);
  }

  @Override
  public void updateCategory(MealCategoryDto categoryDTO) {
    categoryRepository.findByVisibleTrueAndId(categoryDTO.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    categoryRepository.save(getCategoryFromDTO(categoryDTO));
  }

  @Override
  public void deleteCategory(Integer id) {
    var category = categoryRepository.findByVisibleTrueAndId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    var meals = mealRepository.findByVisibleTrueAndCategoryId(id);
    if (!meals.isEmpty()) {
      throw new ResourceInUseException("Category is still used by other meals");
    }
    category.setVisible(false);
    categoryRepository.save(category);
    mealRepository.findByVisibleTrueAndCategoryId(id).forEach(e -> {
      e.setCategoryId(null);
      mealRepository.save(e);
    });
  }

  @Override
  public List<MealCategoryDto> getVisibleCategories() {
    var categoryList = categoryRepository.findByVisibleTrue();
    return categoryList.stream()
            .map(this::getCategoryDTO)
            .collect(Collectors.toList());
  }

  @Override
  public List<MealCategoryDto> getAllCategories() {
    var categoryList = categoryRepository.findAll();
    return categoryList.stream()
            .map(this::getCategoryDTO)
            .collect(Collectors.toList());
  }

  @Override
  public void createMeal(MealDto mealDto) {
    var meal = getMealFromDTO(mealDto);
    mealRepository.save(meal);
  }

  @Override
  public void updateMeal(MealDto mealDto) {
    mealRepository.findByVisibleTrueAndId(mealDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Meal ID not found"));
    var meal = getMealFromDTO(mealDto);
    mealRepository.save(meal);
  }

  @Override
  public void deleteMeal(Long id) {
    var meal = mealRepository.findByVisibleTrueAndId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Meal ID not found"));
    meal.setVisible(false);
    mealRepository.save(meal);
  }

  @Override
  public List<MealDto> getVisibleMeals() {
    return mealRepository.findByVisibleTrue().stream()
            .map(this::getMealDTO).collect(Collectors.toList());
  }

  @Override
  public List<MealDto> getVisibleMeals(String name) {
    return mealRepository.findByVisibleTrueAndNameContains(name).stream()
            .map(this::getMealDTO).collect(Collectors.toList());
  }

  @Override
  public List<MealDto> getAllMeals() {
    return mealRepository.findAll().stream()
            .map(this::getMealDTO).collect(Collectors.toList());
  }

  @Override
  public List<MealDto> getVisibleMealsByCategory(Integer id) {
    return mealRepository.findByVisibleTrueAndCategoryId(id).stream()
            .map(this::getMealDTO).collect(Collectors.toList());
  }

  @Override
  public List<MealDto> getVisibleMealsByCategoryName(String name) {
    var categories= categoryRepository.findByVisibleTrueAndNameContains(name);
    return categories.stream()
            .map(category -> mealRepository.findByVisibleTrueAndCategoryId(category.getId()))
            .flatMap(List::stream).map(this::getMealDTO).toList();
  }
}
