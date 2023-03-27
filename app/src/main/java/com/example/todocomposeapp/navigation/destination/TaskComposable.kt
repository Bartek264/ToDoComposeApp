package com.example.todocomposeapp.navigation.destination

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.navigation.ArgumentsConst.TASK_ARGUMENTS_KEY
import com.example.todocomposeapp.navigation.ScreenConst.TASK_SCREEN
import com.example.todocomposeapp.ui.screen.task.TaskScreen
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
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
		sharedViewModel.getSelectedTask(taskId)
		val selectedTask: RequestState<ToDoEntity> by sharedViewModel.selectedTask.collectAsState()
		TaskScreen(selectedTask, sharedViewModel, navigateToListScreen = navigateToListScreen)
	}

}