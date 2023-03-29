package com.example.todocomposeapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.todocomposeapp.navigation.ScreenConst
import com.example.todocomposeapp.ui.screen.splash.SplashScreen

fun NavGraphBuilder.splashComposable(
	navToListScreen: () -> Unit
) {

	composable(
		route = ScreenConst.SPLASH_SCREEN
	) { navBackStackEntry ->
		SplashScreen(navToListScreen)
	}

}