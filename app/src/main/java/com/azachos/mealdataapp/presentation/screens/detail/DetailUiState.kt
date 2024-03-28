package com.azachos.mealdataapp.presentation.screens.detail

import com.azachos.mealdataapp.domain.models.MealDetails
import com.azachos.mealdataapp.util.ScreenState

data class DetailUiState(
    val mealState: List<MealDetails> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
