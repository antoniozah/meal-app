package com.azachos.mealdataapp.data.dto


import com.google.gson.annotations.SerializedName

data class CategoryMealsDto(
    @SerializedName("meals")
    val meals: List<CategoryMealDto>
)