package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.viewmodel.SharedViewModel

@Composable
fun TaskScreen(
	selectedTask: ToDoEntity?,
	sharedViewModel: SharedViewModel,
	navigateToListScreen: (Action) -> Unit
) {

	Scaffold(
		topBar = { TaskAppBar(selectedTask, navigationToListScreen = navigateToListScreen) },
		content = { pv ->
			TaskContent(
				title = sharedViewModel.title.value,
				onTitleChange = { sharedViewModel.title.value = it },
				description = sharedViewModel.description.value,
				onDescriptionChange = { sharedViewModel.description.value = it },
				priority = sharedViewModel.priority.value,
				onPrioritySelected = { sharedViewModel.priority.value = it }
			)
		}
	)


}