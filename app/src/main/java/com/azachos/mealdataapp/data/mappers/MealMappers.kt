package com.azachos.mealdataapp.data.mappers

import com.azachos.mealdataapp.data.dto.CategoryMealDto
import com.azachos.mealdataapp.data.dto.MealCategoryDto
import com.azachos.mealdataapp.data.dto.MealDetailsDto
import com.azachos.mealdataapp.data.dto.MealDto
import com.azachos.mealdataapp.domain.models.CategoryMeal
import com.azachos.mealdataapp.domain.models.MealCategory
import com.azachos.mealdataapp.domain.models.MealDetails
import com.azachos.mealdataapp.domain.models.RandomRecipeMeal

fun MealDto.toDomain() : RandomRecipeMeal =
    RandomRecipeMeal(
        dateModified = dateModified,
        idMeal = idMeal,
        strArea =  strArea,
        strCategory = strCategory,
        strDrinkAlternate = strDrinkAlternate,
        strInstructions = strInstructions,
        ingredientList = listOf(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13,
            strIngredient14,
            strIngredient15,
            strIngredient16,
            strIngredient17,
            strIngredient18,
            strIngredient19,
            strIngredient20
        ),
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        strSource = strSource ?: "",
        strTags = strTags ?: "",
        strYoutube = strYoutube ?: "",
        measureList = listOf(
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            strMeasure5,
            strMeasure6,
            strMeasure7,
            strMeasure8,
            strMeasure9,
            strMeasure10,
            strMeasure11,
            strMeasure12,
            strMeasure13,
            strMeasure14,
            strMeasure15,
            strMeasure16,
            strMeasure17,
            strMeasure18,
            strMeasure19,
            strMeasure20,
        )
    )

fun MealCategoryDto.toDomain() : MealCategory =
    MealCategory(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryDescription = strCategoryDescription,
        strCategoryThumb = strCategoryThumb
    )

fun CategoryMealDto.toDomain() : CategoryMeal =
    CategoryMeal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )

fun MealDetailsDto.toDomain() : MealDetails =
    MealDetails(
        dateModified = dateModified,
        idMeal = idMeal,
        strArea = strArea,
        strCategory = strCategory,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        strDrinkAlternate = strDrinkAlternate,
        strImageSource = strImageSource,
        ingredientsList = listOf(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13,
            strIngredient14,
            strIngredient15,
            strIngredient16,
            strIngredient17,
            strIngredient18,
            strIngredient19,
            strIngredient20,
        ),
        measuresList = listOf(
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            strMeasure5,
            strMeasure6,
            strMeasure7,
            strMeasure8,
            strMeasure9,
            strMeasure10,
            strMeasure11,
            strMeasure12,
            strMeasure13,
            strMeasure14,
            strMeasure15,
            strMeasure16,
            strMeasure17,
            strMeasure18,
            strMeasure19,
            strMeasure20,
        ),
        strInstructions = strInstructions,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        strSource = strSource,
        strTags = strTags,
        strYoutube = strYoutube
    )