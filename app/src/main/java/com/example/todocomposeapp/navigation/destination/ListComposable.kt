package com.example.todocomposeapp.navigation.destination

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocomposeapp.navigation.ArgumentsConst.LIST_ARGUMENTS_KEY
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN
import com.example.todocomposeapp.ui.screen.list.ListScreen
import com.example.todocomposeapp.viewmodel.SharedViewModel
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
	navigateToTaskScreen: (Long) -> Unit,
	sharedViewModel: SharedViewModel
) {
	composable(
		route = LIST_SCREEN,
		arguments = listOf(navArgument(LIST_ARGUMENTS_KEY) {
			type = NavType.StringType
		})
	) {
		ListScreen(navigateToTaskScreen, sharedViewModel)
	}
}