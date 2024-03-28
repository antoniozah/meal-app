package com.azachos.mealdataapp.domain.models

data class MealDetails(
    val dateModified: Any?,
    val idMeal: String?,
    val strArea: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: String?,
    val strDrinkAlternate: String?,
    val strImageSource: String?,
    val ingredientsList: List<String?>?,
    val measuresList: List<String?>?,
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strSource: String?,
    val strTags: String?,
    val strYoutube: String?
)
