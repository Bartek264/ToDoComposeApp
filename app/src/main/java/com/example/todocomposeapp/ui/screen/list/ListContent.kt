package com.example.todocomposeapp.ui.screen.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.ui.component.EmptyComponent
import com.example.todocomposeapp.ui.theme.HighTaskPriority
import com.example.todocomposeapp.ui.theme.taskItemBackgroundColor
import com.example.todocomposeapp.ui.theme.taskItemTextColor
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.utils.SearchAppBarState

@ExperimentalMaterialApi
@Composable
fun ListContent(
	allTasks: RequestState<List<ToDoEntity>>,
	searchedTasks: RequestState<List<ToDoEntity>>,
	sortState: RequestState<Priority>,
	lowPriorityTasks: List<ToDoEntity>,
	highPriorityTasks: List<ToDoEntity>,
	searchAppBarState: SearchAppBarState,
	navigateToTaskScreen: (taskId: Long) -> Unit,
	onSwipeToDelete: (Action, ToDoEntity) -> Unit
) {
	if (sortState is RequestState.Success) {
		when {
			searchAppBarState == SearchAppBarState.TRIGGERED -> {
				if (searchedTasks is RequestState.Success) {
					HandleListContent(
						tasks = searchedTasks.data,
						onSwipeToDelete = onSwipeToDelete,
						navigateToTaskScreen = navigateToTaskScreen
					)
				}
			}
			sortState.data == Priority.NONE -> {
				if (allTasks is RequestState.Success) {
					HandleListContent(
						tasks = allTasks.data,
						onSwipeToDelete = onSwipeToDelete,
						navigateToTaskScreen = navigateToTaskScreen
					)
				}
			}
			sortState.data == Priority.LOW -> {
				HandleListContent(
					tasks = lowPriorityTasks,
					onSwipeToDelete = onSwipeToDelete,
					navigateToTaskScreen = navigateToTaskScreen
				)
			}
			sortState.data == Priority.HIGH -> {
				HandleListContent(
					tasks = highPriorityTasks,
					onSwipeToDelete = onSwipeToDelete,
					navigateToTaskScreen = navigateToTaskScreen
				)
			}
		}
	}
}

@ExperimentalMaterialApi
@Composable
fun HandleListContent(
	tasks: List<ToDoEntity>,
	onSwipeToDelete: (Action, ToDoEntity) -> Unit,
	navigateToTaskScreen: (taskId: Long) -> Unit
) {
	if (tasks.isEmpty()) {
		EmptyComponent()
	} else {
		DisplayItems(
			toDoList = tasks,
			onSwipeToDelete = onSwipeToDelete,
			navigateToTaskScreen = navigateToTaskScreen
		)
	}
}

@ExperimentalMaterialApi
@Composable
fun DisplayItems(
	toDoList: List<ToDoEntity>,
	onSwipeToDelete: (Action, ToDoEntity) -> Unit,
	navigateToTaskScreen: (taskId: Long) -> Unit
) {
	LazyColumn {
		items(items = toDoList,
			key = { task -> task.id ?: 0 }) { item: ToDoEntity ->
			val dismissState = rememberDismissState()
			val degrees by animateFloatAsState(
				targetValue = if (dismissState.targetValue == DismissValue.Default)
					0f
				else
					-45f
			)

			SwipeToDismiss(
				state = dismissState,
				directions = setOf(DismissDirection.EndToStart),
				dismissThresholds = { FractionalThreshold(0.2f) },
				background = { RedBackground(degree = degrees) }
			) {
				TaskItem(toDoEntity = item, navigateToTaskScreen = navigateToTaskScreen)
			}
		}
	}
}

@Composable
fun RedBackground(degree: Float) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(HighTaskPriority)
			.padding(horizontal = 24.dp),
		contentAlignment = Alignment.CenterEnd
	) {
		Icon(
			modifier = Modifier.rotate(degree),
			imageVector = Icons.Filled.Delete,
			contentDescription = null,
			tint = Color.White
		)
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
		onClick = { navigateToTaskScreen(toDoEntity.id ?: -1) }
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