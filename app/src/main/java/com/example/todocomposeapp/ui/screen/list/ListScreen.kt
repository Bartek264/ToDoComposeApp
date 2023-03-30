package com.example.todocomposeapp.ui.screen.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.theme.fabBackgroundColor
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.utils.SearchAppBarState
import com.example.todocomposeapp.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
	databaseAction: Action,
	navigateToTaskScreen: (Long) -> Unit = {},
	sharedViewModel: SharedViewModel
) {

	LaunchedEffect(key1 = true) {
		sharedViewModel.getAllTask()
		sharedViewModel.readSortState()
	}

	LaunchedEffect(key1 = databaseAction) {
		sharedViewModel.handleActionState(databaseAction)
	}

	val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
	val searchTextState: String by sharedViewModel.searchTextState

	val allTasks: RequestState<List<ToDoEntity>> by sharedViewModel.allTaskList.collectAsState()
	val searchedTask: RequestState<List<ToDoEntity>> by sharedViewModel.searchTaskList.collectAsState()

	val sortState: RequestState<Priority> by sharedViewModel.sortState.collectAsState()
	val lowSortTask: List<ToDoEntity> by sharedViewModel.lowPriorityTasks.collectAsState()
	val highSortTask: List<ToDoEntity> by sharedViewModel.highPriorityTasks.collectAsState()

	val scaffoldState = rememberScaffoldState()

	Snackbar(
		scaffoldState = scaffoldState,
		onComplete = { sharedViewModel.action.value = it },
		onUndoClicked = { sharedViewModel.action.value = it },
		taskTitle = sharedViewModel.title.value,
		action = databaseAction
	)

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = { ListTopBar(sharedViewModel, searchAppBarState, searchTextState) },
		content = { _ ->
			ListContent(
				allTasks = allTasks,
				navigateToTaskScreen = navigateToTaskScreen,
				searchedTasks = searchedTask,
				searchAppBarState = searchAppBarState,
				sortState = sortState,
				lowPriorityTasks = lowSortTask,
				highPriorityTasks = highSortTask,
				onSwipeToDelete = { action, toDoEntity ->
					sharedViewModel.action.value = action
					sharedViewModel.updateSelectedTask(toDoEntity)
				}
			)
		},
		floatingActionButton = { ListFAB(navigateToTaskScreen) }
	)
}

@Composable
private fun ListFAB(navigateToTaskScreen: (Long) -> Unit = {}) {
	FloatingActionButton(
		onClick = { navigateToTaskScreen(-1) },
		backgroundColor = MaterialTheme.colors.fabBackgroundColor
	) {
		Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
	}
}

@Composable
fun Snackbar(
	scaffoldState: ScaffoldState,
	onComplete: (Action) -> Unit,
	onUndoClicked: (Action) -> Unit,
	taskTitle: String,
	action: Action
) {

	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = action) {
		if (action != Action.NO_ACTION) {
			scope.launch {
				val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
					message = "${action.name}: $taskTitle",
					actionLabel = setSnackbarAction(action)
				)
				undoSnackBarAction(action, snackbarResult, onUndoClicked)
			}
			onComplete(Action.NO_ACTION)
		}
	}
}

private fun setSnackbarAction(action: Action): String {
	return if (action == Action.DELETE) {
		"UNDO"
	} else {
		"OK"
	}
}

private fun undoSnackBarAction(
	action: Action,
	snackbarResult: SnackbarResult,
	onUndoClicked: (Action) -> Unit
) {
	if (snackbarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
		onUndoClicked(Action.UNDO)
	}
}