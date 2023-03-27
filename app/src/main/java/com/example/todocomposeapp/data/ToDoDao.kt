package com.example.todocomposeapp.data

import androidx.room.*
import com.example.todocomposeapp.data.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(toDoEntity: ToDoEntity)

	@Query("SELECT * FROM to_do_table WHERE id = :taskId")
	fun getSpecific(taskId: Long): Flow<ToDoEntity>

	@Query("SELECT * FROM to_do_table ORDER BY id ASC")
	fun getAll(): Flow<List<ToDoEntity>>

	@Query("SELECT * FROM to_do_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
	fun searchDatabase(searchQuery: String): Flow<List<ToDoEntity>>

	@Query(
		"SELECT * FROM to_do_table ORDER BY CASE WHEN priority LIKE 'L%' " +
				"THEN 1 WHEN priority LIKE 'M%'" +
				"THEN 2 WHEN priority LIKE 'H%'" +
				"THEN 3 END"
	)
	fun sortByLowPriority(): Flow<List<ToDoEntity>>

	@Query(
		"SELECT * FROM to_do_table ORDER BY CASE WHEN priority LIKE 'H%' " +
				"THEN 1 WHEN priority LIKE 'M%'" +
				"THEN 2 WHEN priority LIKE 'L%'" +
				"THEN 3 END"
	)
	fun sortByHighPriority(): Flow<List<ToDoEntity>>

	@Delete
	fun delete(toDoEntity: ToDoEntity)

	@Query("DELETE FROM to_do_table")
	fun deleteAll()

}