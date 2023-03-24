package com.example.todocomposeapp.ui.screen

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocomposeapp.ui.theme.Purple700

@Composable
fun ListScreen(navigateToTaskScreen: (Long) -> Unit = {}) {
	Scaffold(
		content = {  },
		floatingActionButton = { ListFAB(navigateToTaskScreen) }
	)
}

@Preview(showBackground = true)
@Composable
private fun ListFAB(navigateToTaskScreen: (Long) -> Unit = {}) {
	FloatingActionButton(onClick = { navigateToTaskScreen(1) }, backgroundColor = Purple700) {
		Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
	}
}

@Preview(showSystemUi = true)
@Composable
fun ListScreenPreview() {
	MaterialTheme {
		ListScreen()
	}
}