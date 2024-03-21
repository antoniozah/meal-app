package com.azachos.mealdataapp.data.repository

import com.azachos.mealdataapp.data.NetworkResult
import com.azachos.mealdataapp.data.dto.RandomRecipeResponseDto
import com.azachos.mealdataapp.domain.models.MealCategory
import com.azachos.mealdataapp.domain.models.RandomRecipeMeal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun randomRecipe() : Flow<NetworkResult<List<RandomRecipeMeal>>>
    suspend fun mealCategories() : Flow<NetworkResult<List<MealCategory>>>
}