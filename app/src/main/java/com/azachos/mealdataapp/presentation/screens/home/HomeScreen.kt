package com.azachos.mealdataapp.presentation.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.azachos.mealdataapp.domain.models.CategoryMeal
import com.azachos.mealdataapp.domain.models.MealCategory

@Composable
fun HomeScreen(
    navigateToDetailScreen: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val appContext: Context = LocalContext.current

    LaunchedEffect(key1 = homeUiState.selectedCategory) {
        homeUiState.selectedCategory?.let {
            homeViewModel.getMealsByCategory(it.strCategory)
        }
    }

    when (homeUiState.screenState) {
        HomeViewModel.ScreenState.ERROR -> {
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
        else -> {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            ) {
                MealCategoriesList(
                    categories = homeUiState.mealCategories,
                    onCategoryClick = { homeViewModel.selectCategoryMeal(it) },
                    selectedCategory = homeUiState.selectedCategory?.idCategory
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = if(homeUiState.screenState == HomeViewModel.ScreenState.LOADING) Arrangement.Center else Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(homeUiState.screenState == HomeViewModel.ScreenState.LOADING) {
                        CircularProgressIndicator()
                    } else {
                        homeUiState.selectedCategory?.let {
                            ListContent(
                                meals = homeUiState.mealsByCategory,
                                onMealClicked = navigateToDetailScreen
                            )
                        }
                    }
                }
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

@Composable
fun ListContent(meals: List<CategoryMeal>, onMealClicked: (String) -> Unit) {
    if(meals.isNotEmpty()) {
        MealsByCategoryList(meals = meals, onMealClicked = onMealClicked)
    } else {
        EmptyList()
    }
}

@Composable
fun EmptyList() {
    Column(
        modifier = Modifier.size(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("No meals")
    }
}

@Composable
fun MealsByCategoryList(meals: List<CategoryMeal>, onMealClicked: (String) -> Unit) {
    LazyColumn {
        items(meals) {
            Card(
                modifier = Modifier
                    .clickable {
                        onMealClicked(it.idMeal)
                    }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    model = it.strMealThumb,
                    contentDescription = it.strMeal,
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    text = it.strMeal,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}