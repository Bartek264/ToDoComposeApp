package com.example.todocomposeapp.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocomposeapp.navigation.ArgumentsConst.TASK_ARGUMENTS_KEY
import com.example.todocomposeapp.navigation.ScreenConst.TASK_SCREEN
import com.example.todocomposeapp.ui.screen.task.TaskScreen
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.viewmodel.SharedViewModel

fun NavGraphBuilder.taskComposable(
	navigateToListScreen: (Action) -> Unit,
	sharedViewModel: SharedViewModel
) {
	composable(
		route = TASK_SCREEN,
		arguments = listOf(navArgument(TASK_ARGUMENTS_KEY) {
			type = NavType.LongType
		})
	) { navBackStackEntry ->
		val taskId = navBackStackEntry.arguments!!.getLong(TASK_ARGUMENTS_KEY)
		LaunchedEffect(key1 = taskId) {
			sharedViewModel.getSelectedTask(taskId)
		}
		val selectedTask by sharedViewModel.selectedTask.collectAsState()

		LaunchedEffect(key1 = taskId) {
			if (selectedTask != null || taskId == -1L) {
				sharedViewModel.updateSelectedTask(selectedTask = selectedTask)
			}
		}

		TaskScreen(selectedTask, sharedViewModel, navigateToListScreen = navigateToListScreen)
	}

}