package com.example.todocomposeapp.ui.screen.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.R
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.ui.component.PriorityDropdown

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