package com.example.cleanarchitecture.datasource.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.datasource.data.Task
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getAllTasks(): Single<Array<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task): Completable
}