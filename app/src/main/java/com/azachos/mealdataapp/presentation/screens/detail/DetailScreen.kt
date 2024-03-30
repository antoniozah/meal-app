package com.azachos.mealdataapp.presentation.screens.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.azachos.mealdataapp.domain.models.MealDetails
import com.azachos.mealdataapp.util.ScreenState

@Composable
fun DetailScreen(
    mealId: String,
    navigateToHomeScreen: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState by detailViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = mealId) {
        detailViewModel.getSingleMeal(mealId = mealId.toInt())
    }

   DetailScreenContent(
       screenState = detailUiState.screenState,
       mealDetails = detailUiState.mealState.firstOrNull(),
       errorMessage = detailUiState.errorMessage
   )
}

@Composable
fun DetailScreenContent(
    screenState: ScreenState,
    mealDetails: MealDetails?,
    errorMessage: String?
) {
    when(screenState) {
        ScreenState.SUCCESS -> {
            Log.d("APP___", "Data: $mealDetails")
            mealDetails?.let {
                SuccessContent(
                    mealDetails = it
                )
            }
        }
        ScreenState.LOADING -> {
            LoadingContent()
        }
        ScreenState.ERROR -> {

        }
    }
}

@Composable
fun SuccessContent(mealDetails: MealDetails) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            model = mealDetails.strMealThumb,
            contentDescription = mealDetails.strMeal,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = mealDetails.strMeal ?: "",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}