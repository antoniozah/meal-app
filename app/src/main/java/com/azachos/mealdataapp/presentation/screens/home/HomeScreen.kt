package com.azachos.mealdataapp.presentation.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azachos.mealdataapp.domain.models.MealCategory

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val appContext: Context = LocalContext.current

    when (homeUiState.screenState) {
        HomeViewModel.ScreenState.SUCCESS -> {
            Column {
                Text(
                    text = "MealApp",
                    style = MaterialTheme.typography.titleMedium
                )
                MealCategoriesList(
                    categories = homeUiState.mealCategories,
                    onCategoryClick = { homeViewModel.selectCategoryMeal(it) },
                    selectedCategory = homeUiState.selectedCategory?.idCategory
                )
            }
        }
        HomeViewModel.ScreenState.LOADING -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    onClick = { homeViewModel.getRandomRecipe() }
                )
                Toast.makeText(appContext, homeUiState.errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun AppButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Get Data")
    }
}

@Composable
fun MealCategoriesList(categories: List<MealCategory>, onCategoryClick: (MealCategory) -> Unit, selectedCategory: String?) {
        LazyRow{
            items(categories) { category ->
                val isSelected = selectedCategory == category.idCategory
                FilterChipExample(isSelected = isSelected, onClick = { onCategoryClick(category) }, category = category)
            }
        }
}

@Composable
fun FilterChipExample(isSelected: Boolean, onClick: (MealCategory) -> Unit, category: MealCategory) {
    FilterChip(
        modifier = Modifier.padding(horizontal = 4.dp),
        onClick = { onClick(category) },
        label = {
            Text(text = category.strCategory)
        },
        selected = isSelected
    )
}