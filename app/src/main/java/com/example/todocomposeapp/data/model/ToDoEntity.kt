package com.example.todocomposeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_table")
data class ToDoEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long,
	val title: String,
	val description: String,
	val priority: Priority
)
