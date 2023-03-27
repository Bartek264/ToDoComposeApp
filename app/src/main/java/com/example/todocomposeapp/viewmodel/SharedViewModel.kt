package com.example.todocomposeapp.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocomposeapp.data.model.Priority
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.domain.ToDoRepository
import com.example.todocomposeapp.utils.Action
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
	application: Application,
	private val toDoRepository: ToDoRepository
) : AndroidViewModel(application) {

	val action = mutableStateOf(Action.NO_ACTION)

	val searchAppBarState: MutableState<SearchAppBarState> =
		mutableStateOf(SearchAppBarState.CLOSED)
	val searchTextState: MutableState<String> = mutableStateOf("")

	private val _searchTaskList = MutableStateFlow<RequestState<List<ToDoEntity>>>(RequestState.Idle)
	val searchTaskList: StateFlow<RequestState<List<ToDoEntity>>> get() = _searchTaskList

	fun searchTask(searchQuery: String) {
		_searchTaskList.value = RequestState.Loading
		try {
			viewModelScope.launch {
				toDoRepository.searchDatabase(searchQuery = "%$searchQuery%").collect {
					_searchTaskList.value = RequestState.Success(it)
				}
			}
		} catch (e: Exception) {
			_searchTaskList.value = RequestState.Error(e)
		}
		searchAppBarState.value = SearchAppBarState.TRIGGERED
	}

	private val _allTaskList = MutableStateFlow<RequestState<List<ToDoEntity>>>(RequestState.Idle)
	val allTaskList: StateFlow<RequestState<List<ToDoEntity>>> get() = _allTaskList

	fun getAllTask() = viewModelScope.launch {
		_allTaskList.value = RequestState.Loading
		try {
			toDoRepository.getAllToDo().collect { _allTaskList.value = RequestState.Success(it) }
		} catch (e: Exception) {
			_allTaskList.value = RequestState.Error(e)
		}
	}

	private val _selectedTask = MutableStateFlow<ToDoEntity?>(null)
	val selectedTask: MutableStateFlow<ToDoEntity?> get() = _selectedTask

	fun getSelectedTask(taskId: Long) = viewModelScope.launch {
			toDoRepository.getSpecificTask(taskId).collect { _selectedTask.value = it }
	}

	val id: MutableState<Long?> = mutableStateOf(0)
	val title: MutableState<String> = mutableStateOf("")
	val description: MutableState<String> = mutableStateOf("")
	val priority: MutableState<Priority> = mutableStateOf(Priority.NONE)

	fun updateSelectedTask(selectedTask: ToDoEntity?) {
		id.value = selectedTask?.id
		title.value = selectedTask?.title ?: ""
		description.value = selectedTask?.description ?: ""
		priority.value = selectedTask?.priority ?: Priority.LOW
	}

	fun updateTitle(newTitle: String) {
		if (newTitle.length < 20)
			title.value = newTitle
	}

	private fun insertTask() {
		val toDoEntity = ToDoEntity(
			id.value,
			title.value,
			description.value,
			priority.value
		)
		toDoRepository.insertToDo(toDoEntity)
	}

	private fun deleteTask() {
		val toDoEntity = ToDoEntity(
			id.value,
			title.value,
			description.value,
			priority.value
		)
		toDoRepository.deleteToDo(toDoEntity)
	}

	fun handleActionState(action: Action) {
		when (action) {
			Action.ADD -> insertTask()
			Action.UPDATE -> insertTask()
			Action.UNDO -> {}
			Action.DELETE -> deleteTask()
			Action.DELETE_ALL -> toDoRepository.deleteAll()
			Action.NO_ACTION -> {}
		}
	}

	fun validateFields(): Boolean {
		return title.value.isNotEmpty() && description.value.isNotEmpty()
	}

}