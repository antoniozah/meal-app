package com.azachos.mealdataapp.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azachos.mealdataapp.data.NetworkResult
import com.azachos.mealdataapp.data.repository.MealRepository
import com.azachos.mealdataapp.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getSingleMeal(mealId: Int) {
        viewModelScope.launch {
            mealRepository.singleMeal(mealId).collect {
                when(it) {
                    is NetworkResult.Loading -> {
                        _uiState.update {state ->
                            state.copy(
                                screenState = ScreenState.LOADING
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                errorMessage = it.message,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {state ->
                            state.copy(
                                errorMessage = null,
                                screenState = ScreenState.SUCCESS,
                                mealState = it.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }


}