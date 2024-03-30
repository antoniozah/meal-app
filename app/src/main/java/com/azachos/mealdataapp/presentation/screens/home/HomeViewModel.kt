package com.azachos.mealdataapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azachos.mealdataapp.data.NetworkResult
import com.azachos.mealdataapp.data.repository.MealRepository
import com.azachos.mealdataapp.domain.models.MealCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    enum class ScreenState {
        LOADING,
        SUCCESS,
        ERROR
    }

    init {
        getRandomRecipe()
        getMealCategories()
    }

    fun getRandomRecipe() {
        viewModelScope.launch {
            mealRepository.randomRecipe().collect {
                when(it) {
                    is NetworkResult.Success -> {
                        _uiState.update { homeUiState ->
                            homeUiState.copy(
                                randomRecipe = it.data ?: emptyList(),
                                screenState = ScreenState.SUCCESS
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        it.message?.let {errorValue ->
                            _uiState.update { homeUiState ->
                                homeUiState.copy(
                                    errorMessage = errorValue,
                                    randomRecipe = emptyList(),
                                    screenState = ScreenState.ERROR
                                )
                            }
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { homeUiState ->
                            homeUiState.copy(
                                errorMessage = null,
                                randomRecipe = emptyList(),
                                screenState = ScreenState.LOADING
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getMealCategories() {
        viewModelScope.launch {
            mealRepository.mealCategories().collect {
                when(it) {
                    is NetworkResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.LOADING,
                                errorMessage = null
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.ERROR,
                                errorMessage = it.message,
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.SUCCESS,
                                errorMessage = null,
                                mealCategories = it.data ?: emptyList(),
                                selectedCategory = it.data?.firstOrNull()
                            )
                        }
                    }
                }
            }
        }
    }

    fun selectCategoryMeal(category: MealCategory) {
        _uiState.update { state ->
            state.copy(
                selectedCategory = category
            )
        }
    }

    fun getMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            mealRepository.categoryMeals(categoryName).collect {
                when(it) {
                    is NetworkResult.Loading -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.LOADING,
                                errorMessage = null
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.ERROR,
                                errorMessage = it.message
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                screenState = ScreenState.SUCCESS,
                                mealsByCategory = it.data ?: emptyList(),
                                errorMessage = null,
                            )
                        }
                    }

                }
            }
        }
    }


}