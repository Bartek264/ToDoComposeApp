package com.example.todocomposeapp.ui.screen.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todocomposeapp.ui.theme.splashScreenBackground

@Composable
fun SplashScreen() {
	Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.splashScreenBackground))
}