package com.azachos.mealdataapp.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.azachos.mealdataapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit
) {
    var animation by remember {
        mutableStateOf(false)
    }

//    val alphaState: Float by animateFloatAsState(
//        targetValue = if(animation) 1f else 0f, animationSpec = tween(
//        2000), label = "Splash icon alpha animation"
//    ).asFloatState()

    var currentRotation by remember { mutableFloatStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        animation = true
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(2500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation = value
            scope.launch {
                delay(2501)
                navigateToHomeScreen()
            }
        }

    }

    SplashScreenContent(rotationDegrees = rotation.value)
}

@Composable
fun SplashScreenContent(rotationDegrees: Float) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier
                .size(288.dp)
                .rotate(degrees = rotationDegrees),
//                .alpha(alpha = alphaState),
            painter = painterResource(
                id = if(isSystemInDarkTheme()) R.drawable.meal_logo_dark else R.drawable.meal_logo_light
            ),
            contentDescription = "meal logo",
        )
    }
}