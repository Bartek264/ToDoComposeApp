package com.example.todocomposeapp.data.model

import androidx.compose.ui.graphics.Color
import com.example.todocomposeapp.ui.theme.HighTaskPriority
import com.example.todocomposeapp.ui.theme.LowTaskPriority
import com.example.todocomposeapp.ui.theme.MediumTaskPriority
import com.example.todocomposeapp.ui.theme.NoneTaskPriority

enum class Priority(val color: Color) {
	HIGH(HighTaskPriority),
	MEDIUM(MediumTaskPriority),
	LOW(LowTaskPriority),
	NONE(NoneTaskPriority)
}