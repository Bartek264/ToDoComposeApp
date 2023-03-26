package com.example.todocomposeapp.ui.screen.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todocomposeapp.utils.Action

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit) {

	Scaffold(
		topBar = { TaskAppBar(navigationToListScreen = navigateToListScreen) },
		content = {}
	)



}