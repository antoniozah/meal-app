package com.azachos.mealdataapp.presentation.navigation

import androidx.navigation.NavHostController
import com.azachos.mealdataapp.util.Constants.Navigation.DETAIL_SCREEN_ROUTE
import com.azachos.mealdataapp.util.Constants.Navigation.HOME_SCREEN_ROUTE
import com.azachos.mealdataapp.util.Constants.Navigation.SPLASH_SCREEN_ROUTE

class NavigationScreens(navController: NavHostController) {

    val splash: () -> Unit = {
        navController.navigate(HOME_SCREEN_ROUTE) {
            popUpTo(SPLASH_SCREEN_ROUTE) {
                inclusive = true
            }
        }
    }

    val home: () -> Unit = {
        navController.navigate(HOME_SCREEN_ROUTE) {
            popUpTo(HOME_SCREEN_ROUTE) {
                inclusive = true
            }
        }
    }

    val detail: (String) -> Unit = { mealId ->
        navController.navigate("detail/$mealId") {
            popUpTo(DETAIL_SCREEN_ROUTE) {

            }
            launchSingleTop = true
        }
    }
}