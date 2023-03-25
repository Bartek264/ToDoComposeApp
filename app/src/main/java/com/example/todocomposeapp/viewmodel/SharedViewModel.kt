package com.example.todocomposeapp.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocomposeapp.data.model.ToDoEntity
import com.example.todocomposeapp.domain.ToDoRepository
import com.example.todocomposeapp.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
	application: Application,
	private val toDoRepository: ToDoRepository
) : AndroidViewModel(application) {

	val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
	val searchTextState: MutableState<String> = mutableStateOf("")

	private val _allTaskList = MutableStateFlow<List<ToDoEntity>>(emptyList())
	val allTaskList: StateFlow<List<ToDoEntity>> get() = _allTaskList

	fun getAllTask() = viewModelScope.launch {
		toDoRepository.getAllToDo().collect { _allTaskList.value = it }
	}

}