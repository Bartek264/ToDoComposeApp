package com.example.todocomposeapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.ui.theme.Typography
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarContentColor
import com.example.todocomposeapp.utils.SearchAppBarState
import com.example.todocomposeapp.utils.TrailingIconState
import com.example.todocomposeapp.viewmodel.SharedViewModel

@Composable
fun ListTopBar(
	sharedViewModel: SharedViewModel,
	searchAppBarState: SearchAppBarState,
	searchTextState: String
) {
	when (searchAppBarState) {
		SearchAppBarState.CLOSED -> DefaultTopBar(
			onSearchClicked = {
				sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
			},
			onSortAction = {},
			onDeleteClicked = {})
		else -> SearchTopBar(
			text = searchTextState,
			onTextChange = { sharedViewModel.searchTextState.value = it },
			onCloseClicked = {
				sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
				sharedViewModel.searchTextState.value = ""
			},
			onSearchClicked = { }
		)
	}
}

@Composable
fun DefaultTopBar(
	onSearchClicked: () -> Unit,
	onSortAction: (Priority) -> Unit,
	onDeleteClicked: () -> Unit
) {
	TopAppBar(
		title = {
			Text(
				text = stringResource(id = R.string.list_top_bar_title),
				color = MaterialTheme.colors.topAppBarContentColor
			)
		}, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
		actions = {
			ListAppBarAction(onSearchClicked, onSortAction, onDeleteClicked)
		}
	)
}

@Composable
fun SearchTopBar(
	text: String,
	onTextChange: (String) -> Unit,
	onCloseClicked: () -> Unit,
	onSearchClicked: (String) -> Unit
) {

	var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_DELETE) }

	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.height(56.dp),
		elevation = AppBarDefaults.TopAppBarElevation,
		color = MaterialTheme.colors.topAppBarBackgroundColor
	) {
		TextField(
			modifier = Modifier.fillMaxWidth(),
			value = text,
			singleLine = true,
			onValueChange = { onTextChange(it) },
			placeholder = {
				Text(
					modifier = Modifier.alpha(ContentAlpha.disabled),
					text = stringResource(id = R.string.search),
					color = MaterialTheme.colors.topAppBarContentColor
				)
			},
			leadingIcon = {
				Icon(
					modifier = Modifier.alpha(ContentAlpha.disabled),
					imageVector = Icons.Filled.Search,
					contentDescription = stringResource(id = R.string.search),
					tint = MaterialTheme.colors.topAppBarContentColor
				)
			},
			trailingIcon = {
				IconButton(onClick = {
					when(trailingIconState) {
						TrailingIconState.READY_TO_DELETE -> {
							onTextChange("")
							trailingIconState = TrailingIconState.READY_TO_CLOSE
						}
						TrailingIconState.READY_TO_CLOSE -> {
							if (text.isNotEmpty()) {
								onTextChange("")
							} else {
								onCloseClicked()
								trailingIconState = TrailingIconState.READY_TO_DELETE
							}
						}
					}
				}) {
					Icon(
						imageVector = Icons.Filled.Close,
						contentDescription = null,
						tint = MaterialTheme.colors.topAppBarContentColor
					)
				}
			},
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
			keyboardActions = KeyboardActions { onSearchClicked(text) },
			colors = TextFieldDefaults.textFieldColors(
				cursorColor = MaterialTheme.colors.topAppBarContentColor,
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
				disabledIndicatorColor = Color.Transparent,
				backgroundColor = Color.Transparent
			)
		)
	}
}

@Composable
fun ListAppBarAction(
	onSearchClicked: () -> Unit,
	onSortAction: (Priority) -> Unit,
	onDeleteClicked: () -> Unit
) {
	SearchAction(onSearchClicked = onSearchClicked)
	SortAction(onSortAction = onSortAction)
	DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
	IconButton(onClick = { onSearchClicked() }) {
		Icon(
			imageVector = Icons.Filled.Search,
			contentDescription = stringResource(id = R.string.search),
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
			contentDescription = stringResource(id = R.string.filter),
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

@Composable
fun DeleteAllAction(onDeleteClicked: () -> Unit) {

	var expanded by remember { mutableStateOf(false) }

	IconButton(onClick = { expanded = true }) {
		Icon(
			imageVector = ImageVector.vectorResource(id = R.drawable.dots_menu),
			contentDescription = stringResource(id = R.string.delete_all),
			tint = MaterialTheme.colors.topAppBarContentColor
		)
		DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
			DropdownMenuItem(onClick = {
				expanded = false
				onDeleteClicked()
			}) {
				Icon(
					imageVector = Icons.Filled.Delete,
					contentDescription = stringResource(id = R.string.delete_all)
				)
				Text(
					modifier = Modifier.padding(start = 12.dp),
					text = stringResource(id = R.string.delete_all),
					style = Typography.subtitle1,
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
	DefaultTopBar(onSearchClicked = {}, onSortAction = {}, onDeleteClicked = {})
}

@Preview
@Composable
fun SearchTopBarPreview() {
	SearchTopBar(text = "", onTextChange = { }, onCloseClicked = { }, onSearchClicked = { })
}