package com.example.todocomposeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocomposeapp.data.model.ToDoEntity

@Database(
	entities = [ToDoEntity::class], version = 1, exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {

	abstract fun todoDao(): ToDoDao

}