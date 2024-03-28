package com.azachos.mealdataapp.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.azachos.mealdataapp.presentation.screens.home.HomeScreen
import com.azachos.mealdataapp.util.Constants.Navigation.HOME_SCREEN_ROUTE

fun NavGraphBuilder.homeScreenComposable(
    navigateToDetailScreen: (String) -> Unit
) {
    composable(
        route = HOME_SCREEN_ROUTE
    ) {
        HomeScreen(navigateToDetailScreen = navigateToDetailScreen)
    }
}