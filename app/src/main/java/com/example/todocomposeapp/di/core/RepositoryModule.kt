package com.example.todocomposeapp.di.core

import android.content.Context
import com.example.todocomposeapp.data.ToDoDao
import com.example.todocomposeapp.domain.DataStoreRepository
import com.example.todocomposeapp.domain.ToDoRepository
import com.example.todocomposeapp.domain.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

	@Provides
	@Singleton
	fun provideToDoRepository(toDoDao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(toDoDao)
	@Provides
	@Singleton
	fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository = DataStoreRepository(context)

}