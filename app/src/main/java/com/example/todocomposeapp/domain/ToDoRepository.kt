package com.example.todocomposeapp.domain

import com.example.todocomposeapp.data.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

	fun insertToDo(toDoEntity: ToDoEntity)

	fun getAllToDo(): Flow<List<ToDoEntity>>

	fun getSpecificTask(taskId: Long): Flow<ToDoEntity>

	fun searchDatabase(searchQuery: String): Flow<List<ToDoEntity>>

	fun getSortedListFromLowPriority(): Flow<List<ToDoEntity>>

	fun getSortedListFromHighPriority(): Flow<List<ToDoEntity>>

	fun deleteToDo(toDoEntity: ToDoEntity)

	fun deleteAll()

}