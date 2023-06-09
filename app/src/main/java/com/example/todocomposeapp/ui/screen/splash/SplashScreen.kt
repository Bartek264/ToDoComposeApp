package com.example.todocomposeapp.ui.screen.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.ui.theme.ToDoComposeAppTheme
import com.example.todocomposeapp.ui.theme.splashScreenBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navToListScreen: () -> Unit) {

	/* SplashScreen Animation */
	var startAnimation by remember { mutableStateOf(false) }
	val offsetState by animateDpAsState(
		targetValue = if (startAnimation) 0.dp else 100.dp,
		animationSpec = tween(durationMillis = 1000)
	)
	val alphaState by animateFloatAsState(
		targetValue = if (startAnimation) 1f else 0f,
		animationSpec = tween(durationMillis = 1000)
	)

	LaunchedEffect(key1 = true) {
		startAnimation = true
		delay(3000)
		navToListScreen()
	}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = MaterialTheme.colors.splashScreenBackground),
		contentAlignment = Alignment.Center
	) {
		Image(
			modifier = Modifier.size(120.dp)
				.offset(y = offsetState)
				.alpha(alphaState),
			painter = painterResource(id = getSplashIcon()),
			contentDescription = null
		)
	}
}

@Composable
fun getSplashIcon() = if (isSystemInDarkTheme()) R.drawable.logo_light else R.drawable.logo_dark

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
	SplashScreen() {}
}

@Composable
@Preview
fun SplashScreenPreviewDark() {
	ToDoComposeAppTheme(darkTheme = true) {
		SplashScreen() {}
	}
}