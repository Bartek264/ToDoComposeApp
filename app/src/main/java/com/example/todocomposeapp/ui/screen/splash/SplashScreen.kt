package com.example.todocomposeapp.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.todocomposeapp.R
import com.example.todocomposeapp.ui.theme.splashScreenBackground

@Composable
fun SplashScreen() {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = MaterialTheme.colors.splashScreenBackground),
		contentAlignment = Alignment.Center
	) {
		Icon(imageVector = ImageVector.vectorResource(id = getSplashIcon()), contentDescription = null)
	}
}

@Composable
fun getSplashIcon() = if (isSystemInDarkTheme()) R.drawable.logo_light else R.drawable.logo_dark