package com.azachos.mealdataapp.presentation.screens.home

import com.azachos.mealdataapp.domain.models.MealCategory
import com.azachos.mealdataapp.domain.models.RandomRecipeMeal

data class HomeUiState(
    val randomRecipe: List<RandomRecipeMeal> = emptyList(),
    val screenState: HomeViewModel.ScreenState = HomeViewModel.ScreenState.SUCCESS,
    val errorMessage: String? = null,
    val mealCategories: List<MealCategory> = emptyList(),
    val selectedCategory: MealCategory? = null
)
