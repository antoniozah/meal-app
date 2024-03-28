package com.azachos.mealdataapp.data.dto


import com.google.gson.annotations.SerializedName

data class CategoryMealDto(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)