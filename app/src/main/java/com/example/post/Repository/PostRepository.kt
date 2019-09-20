package com.example.post.Repository

import androidx.lifecycle.LiveData
import com.example.post.Dao.PostDao
import com.example.post.Entity.PostEntity

class PostRepository(private val postDao: PostDao) {

    val allPost: LiveData<List<PostEntity>> = postDao.getAll()

    suspend fun insert(post : PostEntity) {
        postDao.insert(post)
    }
}
