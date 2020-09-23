package com.example.cleanarchitecture.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskDbEntity")
    fun getAllTasks(): Single<List<TaskDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskDbEntity): Completable
}