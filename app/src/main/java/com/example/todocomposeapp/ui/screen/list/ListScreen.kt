package com.example.todocomposeapp.ui.screen.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.component.ListTopBar
import com.example.todocomposeapp.ui.theme.fabBackgroundColor
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.utils.SearchAppBarState
import com.example.todocomposeapp.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(navigateToTaskScreen: (Long) -> Unit = {}, sharedViewModel: SharedViewModel) {

	LaunchedEffect(key1 = true) {
		sharedViewModel.getAllTask()
	}

	val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
	val searchTextState: String by sharedViewModel.searchTextState

	val allTasks: RequestState<List<ToDoEntity>> by sharedViewModel.allTaskList.collectAsState()

	val action by sharedViewModel.action

	val scaffoldState = rememberScaffoldState()

	Snackbar(
		scaffoldState = scaffoldState,
		handleDatabaseAction = { sharedViewModel.handleActionState(action) },
		taskTitle = sharedViewModel.title.value,
		action = action
	)

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = { ListTopBar(sharedViewModel, searchAppBarState, searchTextState) },
		content = {
			ListContent(
				todoState = allTasks,
				navigateToTaskScreen = navigateToTaskScreen
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
	handleDatabaseAction: () -> Unit,
	taskTitle: String,
	action: Action
) {
	handleDatabaseAction()

	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = action) {
		if (action != Action.NO_ACTION) {
			scope.launch {
				val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
					message = "${action.name}: $taskTitle",
					actionLabel = "OK"
				)
			}
		}
	}
}