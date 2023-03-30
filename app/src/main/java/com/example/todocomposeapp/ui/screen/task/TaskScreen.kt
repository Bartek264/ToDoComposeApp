package com.example.todocomposeapp.ui.screen.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
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

	BackHandler(onBackClick = { navigateToListScreen(Action.NO_ACTION) })

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

/**
 * @param backDispatcher interception of back button presses on a mobile device. If no dispatcher is passed, the dispatcher from the local context will be used
 */
@Composable
fun BackHandler(
	backDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
	onBackClick: () -> Unit
) {
	val currentBackPressed by rememberUpdatedState(newValue = onBackClick)

	val backCallback = remember {
		object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				currentBackPressed()
			}
		}
	}

	DisposableEffect(key1 = backCallback) {
		// On screen start
		backDispatcher?.addCallback(backCallback)
		// On screen destroy
		onDispose { backCallback.remove() }
	}

}