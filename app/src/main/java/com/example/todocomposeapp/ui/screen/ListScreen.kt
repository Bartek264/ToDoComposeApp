package com.example.todocomposeapp.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.component.ListContent
import com.example.todocomposeapp.ui.component.ListTopBar
import com.example.todocomposeapp.ui.theme.fabBackgroundColor
import com.example.todocomposeapp.utils.SearchAppBarState
import com.example.todocomposeapp.viewmodel.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun ListScreen(navigateToTaskScreen: (Long) -> Unit = {}, sharedViewModel: SharedViewModel) {

	LaunchedEffect(key1 = true) {
		sharedViewModel.getAllTask()
	}

	val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
	val searchTextState: String by sharedViewModel.searchTextState

	val allTasks: List<ToDoEntity> by sharedViewModel.allTaskList.collectAsState()

	Scaffold(
		topBar = { ListTopBar(sharedViewModel, searchAppBarState, searchTextState) },
		content = { ListContent(toDoList = allTasks, navigateToTaskScreen = navigateToTaskScreen) },
		floatingActionButton = { ListFAB(navigateToTaskScreen) }
	)
}

@Preview(showBackground = true)
@Composable
private fun ListFAB(navigateToTaskScreen: (Long) -> Unit = {}) {
	FloatingActionButton(
		onClick = { navigateToTaskScreen(1) },
		backgroundColor = MaterialTheme.colors.fabBackgroundColor
	) {
		Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
	}
}