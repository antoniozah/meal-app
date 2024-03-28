package com.azachos.mealdataapp.data.api


import com.azachos.mealdataapp.data.dto.CategoryMealsDto
import com.azachos.mealdataapp.data.dto.MealCategoriesDto
import com.azachos.mealdataapp.data.dto.RandomRecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAppService {
    @GET(Constants.RANDOM_RECIPE)
    suspend fun getRandom() : Response<RandomRecipeResponseDto>

    @GET(Constants.MEAL_CATEGORIES)
    suspend fun getMealCategories() : Response<MealCategoriesDto>

    @GET(Constants.MEALS_BY_CATEGORY)
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ) : Response<CategoryMealsDto>
}