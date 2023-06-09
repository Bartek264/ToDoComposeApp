package com.example.todocomposeapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val NoneTaskPriority = MediumGray
val LowTaskPriority = Color(0xFF00C980)
val MediumTaskPriority = Color(0xFFFFC114)
val HighTaskPriority = Color(0xFFFF4646)

val Colors.topAppBarContentColor: Color
	@Composable
	get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
	@Composable
	get() = if (isLight) Purple500 else Color.Black

val Colors.fabBackgroundColor: Color
	@Composable
	get() = if (isLight) Teal200 else Purple700

val Colors.taskItemBackgroundColor: Color
	@Composable
	get() = if (isLight) Color.White else MaterialTheme.colors.background

val Colors.taskItemTextColor: Color
	@Composable
	get() = if (isLight) DarkGray else LightGray

val Colors.splashScreenBackground: Color
	@Composable
	get() = if (isLight) Purple700 else Color.Black