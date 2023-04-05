package com.bobrbolt.gettogether.presentation.adapters

import com.bobrbolt.gettogether.presentation.models.PostModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PostAdapterTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun onCreateViewHolder() {
    }

    @Test
    fun getItemCount() {
        // Arrange
        val postAdapter = PostAdapter()
        val postsList = arrayListOf(
            PostModel(
                authorUsername = "username1",
                images = arrayListOf("", ""),
                description = "description1",
                tagged = arrayListOf("tag1", "tag2")
            ),
            PostModel(
                authorUsername = "username1",
                images = arrayListOf("", ""),
                description = "description1",
                tagged = arrayListOf("tag1", "tag2")
            )
        )

        // Act
        postAdapter.setList(postsList)

        // Assert
        Assert.assertEquals(postsList.size, postAdapter.itemCount)
    }

    @Test
    fun onBindViewHolder() {
    }

    @Test
    fun setList() {
    }

    @Test
    fun addPost() {
    }
}