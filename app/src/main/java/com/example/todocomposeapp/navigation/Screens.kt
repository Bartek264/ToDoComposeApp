package com.example.todocomposeapp.navigation

import androidx.navigation.NavHostController
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN
import com.example.todocomposeapp.utils.Action

class Screens(navController: NavHostController) {

	val list: (Action) -> Unit = { action ->
		navController.navigate("list/${action.name}") {
			popUpTo(LIST_SCREEN) { inclusive = true }
		}
	}

	val task: (Long) -> Unit = { taskId ->
		navController.navigate("task/$taskId")
	}

}