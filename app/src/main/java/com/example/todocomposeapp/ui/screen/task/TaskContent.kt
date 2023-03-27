package com.example.todocomposeapp.ui.screen.task

import androidx.compose.runtime.Composable
import com.example.todocomposeapp.data.model.Priority

@Composable
fun TaskContent(
	title: String,
	onTitleChange: (String) -> Unit,
	description: String,
	onDescriptionChange: (String) -> Unit,
	priority: Priority,
	onPrioritySelected: (Priority) -> Unit
) {

}