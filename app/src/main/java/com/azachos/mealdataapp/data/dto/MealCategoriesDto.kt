package com.azachos.mealdataapp.data.dto


import com.google.gson.annotations.SerializedName

data class MealCategoriesDto(
    @SerializedName("categories")
    val categories: List<MealCategoryDto>
)