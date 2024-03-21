package com.azachos.mealdataapp.data.api


import com.azachos.mealdataapp.data.dto.MealCategoriesDto
import com.azachos.mealdataapp.data.dto.RandomRecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MealAppService {
    @GET(Constants.RANDOM_RECIPE)
    suspend fun getRandom() : Response<RandomRecipeResponseDto>

    @GET(Constants.MEAL_CATEGORIES)
    suspend fun getMealCategories() : Response<MealCategoriesDto>
}