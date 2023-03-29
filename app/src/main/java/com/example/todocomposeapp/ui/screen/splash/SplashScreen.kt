package com.example.todocomposeapp.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.ui.theme.ToDoComposeAppTheme
import com.example.todocomposeapp.ui.theme.splashScreenBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navToListScreen: () -> Unit) {

	LaunchedEffect(key1 = true) {
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
			modifier = Modifier.size(120.dp),
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