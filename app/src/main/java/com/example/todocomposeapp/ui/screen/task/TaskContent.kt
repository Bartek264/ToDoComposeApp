package com.example.todocomposeapp.ui.screen.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.ui.component.PriorityDropdown
import com.example.todocomposeapp.ui.theme.taskItemBackgroundColor
import com.example.todocomposeapp.ui.theme.topAppBarBackgroundColor

@Composable
fun TaskContent(
	title: String,
	onTitleChange: (String) -> Unit,
	description: String,
	onDescriptionChange: (String) -> Unit,
	priority: Priority,
	onPrioritySelected: (Priority) -> Unit
) {
	Column(
		Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = title,
			onValueChange = { onTitleChange(it) },
			label = { Text(text = stringResource(id = R.string.title)) },
			singleLine = true,
			textStyle = MaterialTheme.typography.body1
		)

		Divider(modifier = Modifier.height(8.dp), color = MaterialTheme.colors.background)

		PriorityDropdown(priority = priority, onPrioritySelected = onPrioritySelected)

		OutlinedTextField(
			modifier = Modifier.fillMaxSize(),
			value = description,
			onValueChange = { onDescriptionChange(it) },
			label = { Text(text = stringResource(id = R.string.description)) },
			singleLine = true,
			textStyle = MaterialTheme.typography.body1
		)

	}
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskContent() {
	TaskContent(
		title = "Tile",
		onTitleChange = {},
		description = "",
		onDescriptionChange = {},
		priority = Priority.LOW,
		onPrioritySelected = {}
	)
}