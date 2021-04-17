package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.taskdomain.model.Task
import org.junit.Assert
import org.junit.Test

class TaskEntityMapperTest {

    private val mapper = TaskDbEntityMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToTask() {
        val taskEntity = TaskDbEntity(title)
        val task = mapper.map(taskEntity)
        Assert.assertEquals(title, task.title)
    }

    @Test
    fun reverseTest_shouldMapToTaskEntity() {
        val task = Task(title)
        val taskDto = mapper.reverse(task)
        Assert.assertEquals(title, taskDto.title)
    }

    @Test
    fun mapTest_emptyList() {
        val mappedList = mapper.map(emptyList())
        Assert.assertEquals(0, mappedList.size)
    }

    @Test
    fun mapTest_notEmptyList() {
        val task = TaskDbEntity(title)
        val mappedList = mapper.map(listOf(task, task, task, task))
        Assert.assertEquals(4, mappedList.size)
        Assert.assertEquals(title, mappedList[0].title)
    }
}