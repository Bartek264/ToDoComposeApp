package com.example.todocomposeapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.theme.taskItemBackgroundColor
import com.example.todocomposeapp.ui.theme.taskItemTextColor

@ExperimentalMaterialApi
@Composable
fun ListContent(toDoList: List<ToDoEntity>, navigateToTaskScreen: (taskId: Long) -> Unit) {
	LazyColumn {
		items(items = toDoList,
			key = { task -> task.id }) { item: ToDoEntity ->
			TaskItem(toDoEntity = item, navigateToTaskScreen = navigateToTaskScreen)
		}
	}
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(toDoEntity: ToDoEntity, navigateToTaskScreen: (taskId: Long) -> Unit) {
	Surface(
		modifier = Modifier.fillMaxWidth(),
		color = MaterialTheme.colors.taskItemBackgroundColor,
		shape = RectangleShape,
		elevation = 2.dp,
		onClick = { navigateToTaskScreen(toDoEntity.id) }
	) {
		Column(
			modifier = Modifier
				.padding(12.dp)
				.fillMaxWidth()
		) {
			Row {
				Text(
					modifier = Modifier.weight(8f),
					text = toDoEntity.title,
					color = MaterialTheme.colors.taskItemTextColor,
					style = MaterialTheme.typography.h5,
					fontWeight = FontWeight.Bold,
					maxLines = 1
				)
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.weight(1f), contentAlignment = Alignment.TopEnd
				) {
					Canvas(modifier = Modifier.size(16.dp)) {
						drawCircle(color = toDoEntity.priority.color)
					}
				}
			}
			Text(
				modifier = Modifier.fillMaxWidth(),
				text = toDoEntity.description,
				color = MaterialTheme.colors.taskItemTextColor,
				style = MaterialTheme.typography.subtitle1,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TaskItemPreview() {
	TaskItem(
		toDoEntity = ToDoEntity(1, "Simple title", "Simple description", Priority.MEDIUM),
		navigateToTaskScreen = {})
}