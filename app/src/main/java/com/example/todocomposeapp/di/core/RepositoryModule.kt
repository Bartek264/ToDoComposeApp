package com.example.todocomposeapp.di.core

import com.example.todocomposeapp.data.ToDoDao
import com.example.todocomposeapp.domain.ToDoRepository
import com.example.todocomposeapp.domain.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

	@Provides
	@Singleton
	fun provideToDoRepository(toDoDao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(toDoDao)

}