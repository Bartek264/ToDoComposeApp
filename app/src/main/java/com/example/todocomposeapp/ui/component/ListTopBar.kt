package com.example.todocomposeapp.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarContentColor

@Composable
fun ListTopBar() {
	DefaultTopBar(onSearchClicked = {})
}

@Composable
fun DefaultTopBar(onSearchClicked: () -> Unit) {
	TopAppBar(
		title = {
			Text(
				text = stringResource(id = R.string.list_top_bar_title),
				color = MaterialTheme.colors.topAppBarContentColor
			)
		}, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
		actions = {
			ListAppBarAction(onSearchClicked)
		}
	)
}

@Composable
fun ListAppBarAction(onSearchClicked: () -> Unit) {
	SearchAction(onSearchClicked)
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
//	IconButton(onClick = { onSortAction() }) {
//		Icon(imageVector = Icons.Filled.fi, contentDescription = )
//	}
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
	DefaultTopBar(onSearchClicked = {})
}