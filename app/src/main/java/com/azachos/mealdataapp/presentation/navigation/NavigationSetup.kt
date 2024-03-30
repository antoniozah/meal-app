package com.azachos.mealdataapp.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.azachos.mealdataapp.presentation.navigation.destinations.detailScreenComposable
import com.azachos.mealdataapp.presentation.navigation.destinations.homeScreenComposable
import com.azachos.mealdataapp.presentation.navigation.destinations.splashScreenComposable
import com.azachos.mealdataapp.util.Constants.Navigation.MainApp.HOME_SCREEN_ROUTE
import com.azachos.mealdataapp.util.Constants.Navigation.MainApp.MAIN_APP_NAV_ROUTE
import com.azachos.mealdataapp.util.Constants.Navigation.Splash.SPLASH_SCREEN_ROUTE

@SuppressLint("RestrictedApi")
@Composable
fun NavigationSetup(navController: NavHostController) {

    val screen = remember(navController) {
        NavigationScreens(navController)
    }

    NavHost(navController = navController, startDestination = SPLASH_SCREEN_ROUTE) {

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

@Composable
fun MainAppNavigation(navController: NavHostController, screen: NavigationScreens) {
    NavHost(navController = navController, startDestination = HOME_SCREEN_ROUTE) {

    }
}