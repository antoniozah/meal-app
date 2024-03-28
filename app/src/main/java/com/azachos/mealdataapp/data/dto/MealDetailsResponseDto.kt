package com.azachos.mealdataapp.data.dto


import com.google.gson.annotations.SerializedName

data class MealDetailsResponseDto(
    @SerializedName("meals")
    val meals: List<MealDetailsDto>
)