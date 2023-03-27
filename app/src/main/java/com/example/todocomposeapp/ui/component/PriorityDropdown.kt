package com.example.todocomposeapp.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.data.model.Priority

@Composable
fun PriorityDropdown(
	priority: Priority,
	onPrioritySelected: (Priority) -> Unit
) {

	var expended by remember { mutableStateOf(false) }

	val angle: Float by animateFloatAsState(targetValue = if (expended) 180f else 0f)

	Row(modifier = Modifier
		.fillMaxWidth()
		.height(60.dp)
		.clickable { expended = !expended }
		.border(
			width = 1.dp,
			color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
			shape = MaterialTheme.shapes.small
		),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Canvas(modifier = Modifier
			.size(12.dp)
			.weight(1f)) {
			drawCircle(color = priority.color)
		}
		Text(
			modifier = Modifier.weight(8f),
			text = priority.name,
			style = MaterialTheme.typography.subtitle2
		)
		IconButton(modifier = Modifier
			.alpha(ContentAlpha.disabled)
			.rotate(angle)
			.weight(1.5f),
			onClick = { expended = true }) {
			Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
		}
		DropdownMenu(modifier = Modifier.fillMaxWidth(), expanded = expended, onDismissRequest = { expended = false }) {
			DropdownMenuItem(onClick = {
				expended = false
				onPrioritySelected(Priority.NONE)
			}) {
				PriorityItem(priority = Priority.NONE)
			}
			DropdownMenuItem(onClick = {
				expended = false
				onPrioritySelected(Priority.LOW)
			}) {
				PriorityItem(priority = Priority.LOW)
			}
			DropdownMenuItem(onClick = {
				expended = false
				onPrioritySelected(Priority.MEDIUM)
			}) {
				PriorityItem(priority = Priority.MEDIUM)
			}
			DropdownMenuItem(onClick = {
				expended = false
				onPrioritySelected(Priority.HIGH)
			}) {
				PriorityItem(priority = Priority.HIGH)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewPriorityDropdown() {
	PriorityDropdown(priority = Priority.MEDIUM, onPrioritySelected = {})
}