package com.azachos.mealdataapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.azachos.mealdataapp.presentation.navigation.destinations.detailScreenComposable
import com.azachos.mealdataapp.presentation.navigation.destinations.homeScreenComposable
import com.azachos.mealdataapp.presentation.navigation.destinations.splashScreenComposable
import com.azachos.mealdataapp.util.Constants.Navigation.HOME_SCREEN_ROUTE
import com.azachos.mealdataapp.util.Constants.Navigation.SPLASH_SCREEN_ROUTE

@Composable
fun NavigationSetup(navController: NavHostController) {

    val screen = remember(navController) {
        NavigationScreens(navController)
    }

    NavHost(navController = navController, startDestination = SPLASH_SCREEN_ROUTE ) {
        splashScreenComposable(
            navigateToHomeScreen = screen.splash
        )
        homeScreenComposable(
            navigateToDetailScreen = screen.detail
        )
        detailScreenComposable(
            navigateToHomeScreen = screen.home
        )
    }

}