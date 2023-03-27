package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.viewmodel.SharedViewModel

@Composable
fun TaskScreen(selectedTask: RequestState<ToDoEntity?>,
			   sharedViewModel: SharedViewModel,
			   navigateToListScreen: (Action) -> Unit) {

	Scaffold(
		topBar = { TaskAppBar(selectedTask, navigationToListScreen = navigateToListScreen) },
		content = { pv ->
			TaskContent(
				title = sharedViewModel.title.value,
				onTitleChange = { sharedViewModel.title.value = it },
				description = if (selectedTask is RequestState.Success) selectedTask.data?.description ?: "" else "",
				onDescriptionChange = { sharedViewModel.description.value = it },
				priority = if (selectedTask is RequestState.Success) selectedTask.data?.priority ?: Priority.LOW else Priority.LOW,
				onPrioritySelected = { sharedViewModel.priority.value = it }
			)
		}
	)


}