package com.azachos.mealdataapp.domain.models

import com.google.gson.annotations.SerializedName

data class MealCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)
