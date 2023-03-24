package com.example.todocomposeapp.domain

import com.example.todocomposeapp.data.ToDoDao
import com.example.todocomposeapp.data.model.ToDoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {

	override suspend fun insertToDo(toDoEntity: ToDoEntity) {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.insert(toDoEntity) }
	}

	override suspend fun getAllToDo(): Flow<List<ToDoEntity>> = toDoDao.getAll()

	override suspend fun getSpecificTask(taskId: Long): Flow<ToDoEntity> = toDoDao.getSpecific(taskId)

	override suspend fun getSortedListFromLowPriority(): Flow<List<ToDoEntity>> = toDoDao.sortByLowPriority()

	override suspend fun getSortedListFromHighPriority(): Flow<List<ToDoEntity>> = toDoDao.sortByHighPriority()

	override suspend fun deleteToDo(toDoEntity: ToDoEntity) {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.delete(toDoEntity) }
	}

	override suspend fun deleteAll() {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.deleteAll() }
	}
}