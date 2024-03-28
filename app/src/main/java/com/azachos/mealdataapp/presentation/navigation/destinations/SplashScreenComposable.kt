package com.azachos.mealdataapp.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.azachos.mealdataapp.presentation.screens.splash.SplashScreen
import com.azachos.mealdataapp.util.Constants.Navigation.SPLASH_SCREEN_ROUTE

fun NavGraphBuilder.splashScreenComposable(
    navigateToHomeScreen:() -> Unit
) {
    composable(route = SPLASH_SCREEN_ROUTE) {
        SplashScreen(navigateToHomeScreen = navigateToHomeScreen)
    }
}