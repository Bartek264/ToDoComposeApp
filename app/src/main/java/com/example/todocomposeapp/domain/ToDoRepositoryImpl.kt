package com.example.todocomposeapp.domain

import com.example.todocomposeapp.data.ToDoDao
import com.example.todocomposeapp.data.model.ToDoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {

	override fun insertToDo(toDoEntity: ToDoEntity) {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.insert(toDoEntity) }
	}

	override fun getAllToDo(): Flow<List<ToDoEntity>> = toDoDao.getAll()

	override fun getSpecificTask(taskId: Long): Flow<ToDoEntity> = toDoDao.getSpecific(taskId)

	override fun getSortedListFromLowPriority(): Flow<List<ToDoEntity>> = toDoDao.sortByLowPriority()

	override fun getSortedListFromHighPriority(): Flow<List<ToDoEntity>> = toDoDao.sortByHighPriority()

	override fun deleteToDo(toDoEntity: ToDoEntity) {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.delete(toDoEntity) }
	}

	override fun deleteAll() {
		CoroutineScope(Dispatchers.IO).launch { toDoDao.deleteAll() }
	}
}