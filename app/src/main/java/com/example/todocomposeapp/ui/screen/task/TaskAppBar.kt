package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.todocomposeapp.R
import com.example.todocomposeapp.ui.component.ListAppBarAction
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarContentColor
import com.example.todocomposeapp.utils.Action

@Composable
fun TaskAppBar() {

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

		}
	)
}

@Composable
fun BackAction(onBackClick: (Action) -> Unit) {
	IconButton(onClick = { onBackClick(Action.NO_ACTION) }) {
		Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
	}
}