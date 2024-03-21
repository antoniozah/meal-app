package com.azachos.mealdataapp.domain.models

import com.google.gson.annotations.SerializedName

data class RandomRecipeMeal(
    @SerializedName("dateModified")
    val dateModified: Any?,
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strArea")
    val strArea: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: Any?,
    val ingredientList: List<String>,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    val measureList: List<String>,
    @SerializedName("strSource")
    val strSource: String,
    @SerializedName("strTags")
    val strTags: String,
    @SerializedName("strYoutube")
    val strYoutube: String
)
