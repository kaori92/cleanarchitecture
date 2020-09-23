package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.source.remote.model.TaskApiDto
import com.example.cleanarchitecture.domain.model.Task
import org.junit.Assert
import org.junit.Test

class TaskApiDtoMapperTest {

    val mapper = TaskApiDtoMapper()
    private val title = "Test"

    @Test
    fun mapTest_shouldMapToTask() {
        val taskDto = TaskApiDto(title)
        val task = mapper.map(taskDto)
        Assert.assertEquals(task.title, title)
    }

    @Test
    fun reverseTest_shouldMapToTaskApiDto() {
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
        val task = TaskApiDto(title)
        val mappedList = mapper.map(listOf(task, task, task, task))
        Assert.assertEquals(mappedList.size, 4)
        Assert.assertEquals(mappedList[0].title, title)
    }

}