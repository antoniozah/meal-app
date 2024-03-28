package com.azachos.mealdataapp.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.azachos.mealdataapp.presentation.screens.detail.DetailScreen
import com.azachos.mealdataapp.util.Constants.Navigation.DETAIL_SCREEN_ROUTE

fun NavGraphBuilder.detailScreenComposable(
    navigateToHomeScreen:() -> Unit
) {
    composable(
        route = DETAIL_SCREEN_ROUTE,
        arguments = listOf(navArgument("mealId") {
            type = NavType.StringType
        })
    ) {
        val mealId = it.arguments?.getString("mealId") ?: "-1"
        DetailScreen(
            mealId = mealId,
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}