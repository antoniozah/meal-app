package com.azachos.mealdataapp.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.azachos.mealdataapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit
) {
    var animation by remember {
        mutableStateOf(false)
    }

    val alphaState: Float by animateFloatAsState(
        targetValue = if(animation) 1f else 0f, animationSpec = tween(
        1000)).asFloatState()

    LaunchedEffect(key1 = true) {
        animation = true
        delay(2000)
        navigateToHomeScreen()
    }

    SplashScreenContent(alphaState = alphaState)
}

@Composable
fun SplashScreenContent(alphaState: Float) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier.
            size(92.dp)
                .alpha(alpha = alphaState),
            painter = painterResource(
                id = if(isSystemInDarkTheme()) R.drawable.meal_logo_dark else R.drawable.meal_logo_light
            ),
            contentDescription = "meal logo"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "MealApp", style = MaterialTheme.typography.headlineMedium)
    }
}