package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarContentColor
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState

@Composable
fun TaskAppBar(selectedTask: RequestState<ToDoEntity?>, navigationToListScreen: (Action) -> Unit) {
	if (selectedTask is RequestState.Success) {
		if (selectedTask.data == null) {
			NewTaskAppBar(navigationToListScreen)
		} else {
			ExistingTaskAppBar(
				selectedTask = selectedTask.data,
				navigateToTaskList = navigationToListScreen
			)
		}
	}
}

@Composable
fun NewTaskAppBar(
	navigationToListScreen: (Action) -> Unit
) {
	TopAppBar(
		navigationIcon = { BackAction(onBackClick = navigationToListScreen) },
		title = {
			Text(
				text = stringResource(id = R.string.new_task_title),
				color = MaterialTheme.colors.topAppBarContentColor
			)
		}, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
		actions = {
			AddAction(onAddClick = navigationToListScreen)
		}
	)
}

@Composable
fun ExistingTaskAppBar(
	selectedTask: ToDoEntity,
	navigateToTaskList: (Action) -> Unit
) {
	TopAppBar(
		navigationIcon = { CloseButton(onCloseClick = navigateToTaskList) },
		title = {
			Text(
				text = selectedTask.title,
				color = MaterialTheme.colors.topAppBarContentColor,
				overflow = TextOverflow.Ellipsis,
				maxLines = 1
			)
		},
		backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
		actions = {
			DeleteTask(onDeleteClick = navigateToTaskList)
			UpdateTask(onUpdateTask = navigateToTaskList)
		}
	)
}

@Composable
fun UpdateTask(onUpdateTask: (Action) -> Unit) {
	IconButton(onClick = { onUpdateTask(Action.UPDATE) }) {
		Icon(
			imageVector = Icons.Filled.Check,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Composable
fun DeleteTask(onDeleteClick: (Action) -> Unit) {
	IconButton(onClick = { onDeleteClick(Action.DELETE) }) {
		Icon(
			imageVector = Icons.Filled.Delete,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Composable
fun CloseButton(onCloseClick: (Action) -> Unit) {
	IconButton(onClick = { onCloseClick(Action.NO_ACTION) }) {
		Icon(
			imageVector = Icons.Filled.Close,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Composable
fun BackAction(onBackClick: (Action) -> Unit) {
	IconButton(onClick = { onBackClick(Action.NO_ACTION) }) {
		Icon(
			imageVector = Icons.Filled.ArrowBack,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Composable
fun AddAction(onAddClick: (Action) -> Unit) {
	IconButton(onClick = { onAddClick(Action.ADD) }) {
		Icon(
			imageVector = Icons.Filled.Check,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Preview
@Composable
fun NewTaskPreview() {
	NewTaskAppBar(navigationToListScreen = {})
}

@Preview
@Composable
fun ExistingTaskPreview() {
	ExistingTaskAppBar(selectedTask = ToDoEntity(0, "Sample title", "Description", Priority.LOW), navigateToTaskList = {})
}