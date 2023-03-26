package com.example.todocomposeapp.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.domain.ToDoRepository
import com.example.todocomposeapp.utils.RequestState
import com.example.todocomposeapp.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
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

	val searchAppBarState: MutableState<SearchAppBarState> =
		mutableStateOf(SearchAppBarState.CLOSED)
	val searchTextState: MutableState<String> = mutableStateOf("")

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

	private val _selectedTask = MutableStateFlow<RequestState<ToDoEntity>>(RequestState.Idle)
	val selectedTask: StateFlow<RequestState<ToDoEntity>> get() = _selectedTask

	fun getSelectedTask(taskId: Long) = viewModelScope.launch {
		_selectedTask.value = RequestState.Loading
		try {
			toDoRepository.getSpecificTask(taskId).collect { _selectedTask.value = RequestState.Success(it) }
		} catch (e: Exception) {
			_selectedTask.value = RequestState.Error(e)
		}
	}

}