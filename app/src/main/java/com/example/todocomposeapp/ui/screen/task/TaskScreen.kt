package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState

@Composable
fun TaskScreen(selectedTask: RequestState<ToDoEntity?>, navigateToListScreen: (Action) -> Unit) {

	if (selectedTask is RequestState.Success) {
		if (selectedTask.data == null) {
			NewTaskAppBar(navigateToListScreen)
		} else {
			ExistingTaskAppBar(
				selectedTask = selectedTask.data,
				navigateToTaskList = navigateToListScreen
			)
		}
	}

	Scaffold(
		topBar = { TaskAppBar(navigationToListScreen = navigateToListScreen) },
		content = { pv ->
			TaskContent(
				title = "Tile",
				onTitleChange = {},
				description = "",
				onDescriptionChange = {},
				priority = Priority.LOW,
				onPrioritySelected = {}
			)
		}
	)


}