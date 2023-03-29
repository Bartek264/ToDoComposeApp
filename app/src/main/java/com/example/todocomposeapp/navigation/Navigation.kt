package com.example.todocomposeapp.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todocomposeapp.navigation.ScreenConst.SPLASH_SCREEN
import com.example.todocomposeapp.navigation.destination.listComposable
import com.example.todocomposeapp.navigation.destination.splashComposable
import com.example.todocomposeapp.navigation.destination.taskComposable
import com.example.todocomposeapp.viewmodel.SharedViewModel
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {

	val screens = remember(navController) {
		Screens(navController)
	}

	NavHost(
		navController = navController,
		startDestination = SPLASH_SCREEN
	) {
		splashComposable(screens.splash)
		listComposable(screens.list, sharedViewModel)
		taskComposable(screens.task, sharedViewModel)
	}

}