package com.example.todocomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN
import com.example.todocomposeapp.navigation.destination.listComposable
import com.example.todocomposeapp.navigation.destination.taskComposable
import com.example.todocomposeapp.viewmodel.SharedViewModel

@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {

	val screens = remember(navController) {
		Screens(navController)
	}

	NavHost(
		navController = navController,
		startDestination = LIST_SCREEN
	) {
		listComposable(screens.task, sharedViewModel)
		taskComposable(screens.list, sharedViewModel)
	}

}