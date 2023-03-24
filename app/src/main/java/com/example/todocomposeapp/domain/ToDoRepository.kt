package com.example.todocomposeapp.domain

import com.example.todocomposeapp.data.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

	suspend fun insertToDo(toDoEntity: ToDoEntity)

	suspend fun getAllToDo(): Flow<List<ToDoEntity>>

	suspend fun getSpecificTask(taskId: Long): Flow<ToDoEntity>

	suspend fun getSortedListFromLowPriority(): Flow<List<ToDoEntity>>

	suspend fun getSortedListFromHighPriority(): Flow<List<ToDoEntity>>

	suspend fun deleteToDo(toDoEntity: ToDoEntity)

	suspend fun deleteAll()

}