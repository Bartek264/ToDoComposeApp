package com.example.todocomposeapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocomposeapp.navigation.ArgumentsConst.LIST_ARGUMENTS_KEY
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN

fun NavGraphBuilder.listComposable(
	navigateToTaskScreen: (Long) -> Unit
) {
	composable(
		route = LIST_SCREEN,
		arguments = listOf(navArgument(LIST_ARGUMENTS_KEY) {
			type = NavType.StringType
		})
	) {

	}
}