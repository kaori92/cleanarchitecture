package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.source.local.model.TaskDbEntity
import com.example.cleanarchitecture.domain.model.Task
import org.junit.Assert
import org.junit.Test

class TaskEntityMapperTest {

    private val mapper = TaskDbEntityMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToTask() {
        val taskEntity = TaskDbEntity(title)
        val task = mapper.map(taskEntity)
        Assert.assertEquals(task.title, title)
    }

    @Test
    fun reverseTest_shouldMapToTaskEntity() {
        val task = Task(title)
        val taskDto = mapper.reverse(task)
        Assert.assertEquals(taskDto.title, title)
    }

    @Test
    fun mapTest_emptyList() {
        val mappedList = mapper.map(emptyList())
        Assert.assertEquals(mappedList.size, 0)
    }

    @Test
    fun mapTest_notEmptyList() {
        val task = TaskDbEntity(title)
        val mappedList = mapper.map(listOf(task, task, task, task))
        Assert.assertEquals(mappedList.size, 4)
        Assert.assertEquals(mappedList[0].title, title)
    }
}