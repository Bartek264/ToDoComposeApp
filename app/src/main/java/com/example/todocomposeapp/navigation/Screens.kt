package com.example.todocomposeapp.navigation

import androidx.navigation.NavHostController
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN
import com.example.todocomposeapp.navigation.ScreenConst.SPLASH_SCREEN
import com.example.todocomposeapp.utils.Action

class Screens(navController: NavHostController) {

	val splash: () -> Unit = {
		navController.navigate(route = "list/${Action.NO_ACTION.name}") {
			popUpTo(SPLASH_SCREEN) { inclusive = true }
		}
	}

	val task: (Action) -> Unit = { action ->
		navController.navigate("list/${action.name}") {
			popUpTo(LIST_SCREEN) { inclusive = true }
		}
	}

	val list: (Long) -> Unit = { taskId ->
		navController.navigate("task/$taskId")
	}

}