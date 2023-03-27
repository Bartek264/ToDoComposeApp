package com.example.todocomposeapp.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.viewmodel.SharedViewModel

@Composable
fun TaskScreen(
	selectedTask: ToDoEntity?,
	sharedViewModel: SharedViewModel,
	navigateToListScreen: (Action) -> Unit
) {

	val context = LocalContext.current

	Scaffold(
		topBar = {
			TaskAppBar(selectedTask, navigationToListScreen = { action ->
				if (action == Action.NO_ACTION) {
					navigateToListScreen(action)
				} else {
					if (sharedViewModel.validateFields()) {
						navigateToListScreen(action)
					} else {
						displayToast(context)
					}
				}
			})
		},
		content = { pv ->
			TaskContent(
				title = sharedViewModel.title.value,
				onTitleChange = { sharedViewModel.updateTitle(it) },
				description = sharedViewModel.description.value,
				onDescriptionChange = { sharedViewModel.description.value = it },
				priority = sharedViewModel.priority.value,
				onPrioritySelected = { sharedViewModel.priority.value = it }
			)
		}
	)
}

fun displayToast(context: Context) {
	Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
}