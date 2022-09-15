package com.example.kimchiroom

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
  @Insert
  suspend fun insert(employeeEntity: EmployeeEntity)

  @Update
  suspend fun update(employeeEntity: EmployeeEntity)

  @Delete
  suspend fun delete(employeeEntity: EmployeeEntity)

  @Query("SELECT * FROM `employee-table`")
  fun fetchAllEmployees(): Flow<List<EmployeeEntity>>
  // Flow belongs to coroutine class notify something change

  @Query("SELECT * FROM `employee-table` where id=:id" )
  fun fetchAllEmployeeById(id: Int): Flow<EmployeeEntity>
  // Flow belongs to coroutine class notify something change

}