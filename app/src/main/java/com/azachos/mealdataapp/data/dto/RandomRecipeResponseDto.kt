package com.azachos.mealdataapp.data.dto


import com.google.gson.annotations.SerializedName

data class RandomRecipeResponseDto(
    @SerializedName("meals")
    val meals: List<MealDto>
)