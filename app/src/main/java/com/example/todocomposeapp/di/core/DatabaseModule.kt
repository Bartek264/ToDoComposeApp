package com.example.todocomposeapp.di.core

import android.app.Application
import androidx.room.Room
import com.example.todocomposeapp.data.ToDoDao
import com.example.todocomposeapp.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

	@Provides
	@Singleton
	fun provideToDoDatabase(app: Application): ToDoDatabase = Room.databaseBuilder(app, ToDoDatabase::class.java, "todo_client").build()

	@Provides
	@Singleton
	fun provideToDoDao(toDoDatabase: ToDoDatabase): ToDoDao = toDoDatabase.todoDao()

}