package com.example.todocomposeapp.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarContentColor

@Composable
fun ListTopBar() {
	DefaultTopBar(onSearchClicked = {}, onSortAction = {})
}

@Composable
fun DefaultTopBar(onSearchClicked: () -> Unit, onSortAction: (Priority) -> Unit) {
	TopAppBar(
		title = {
			Text(
				text = stringResource(id = R.string.list_top_bar_title),
				color = MaterialTheme.colors.topAppBarContentColor
			)
		}, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
		actions = {
			ListAppBarAction(onSearchClicked, onSortAction)
		}
	)
}

@Composable
fun ListAppBarAction(onSearchClicked: () -> Unit, onSortAction: (Priority) -> Unit) {
	SearchAction(onSearchClicked = onSearchClicked)
	SortAction(onSortAction = onSortAction)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
	IconButton(onClick = { onSearchClicked() }) {
		Icon(
			imageVector = Icons.Filled.Search,
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
	}
}

@Composable
fun SortAction(onSortAction: (Priority) -> Unit) {

	var expended by remember { mutableStateOf(false) }

	IconButton(onClick = { expended = true }) {
		Icon(
			imageVector = ImageVector.vectorResource(id = R.drawable.filter_list),
			contentDescription = null,
			tint = MaterialTheme.colors.topAppBarContentColor
		)
		DropdownMenu(expanded = expended,
			onDismissRequest = { expended = false }) {
			DropdownMenuItem(onClick = {
				expended = false
				onSortAction(Priority.NONE)
			}) { PriorityItem(priority = Priority.NONE) }
			DropdownMenuItem(onClick = {
				expended = false
				onSortAction(Priority.LOW)
			}) { PriorityItem(priority = Priority.LOW) }
			DropdownMenuItem(onClick = {
				expended = false
				onSortAction(Priority.MEDIUM)
			}) { PriorityItem(priority = Priority.MEDIUM) }
			DropdownMenuItem(onClick = {
				expended = false
				onSortAction(Priority.HIGH)
			}) { PriorityItem(priority = Priority.HIGH) }
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
	DefaultTopBar(onSearchClicked = {}, onSortAction = {})
}