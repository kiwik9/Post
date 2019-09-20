package com.example.post.Repository

import androidx.lifecycle.LiveData
import com.example.post.Dao.CommentDao
import com.example.post.Entity.CommentEntity

class CommentRepository(private val commentDao: CommentDao) {

    val allPost: LiveData<List<CommentEntity>> = commentDao.getAll()

    suspend fun insert(comment : CommentEntity) {
        commentDao.insert(comment)
    }
}
