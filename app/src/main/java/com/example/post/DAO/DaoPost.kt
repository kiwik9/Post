package com.example.post.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.post.Entity.PostEntity

@Dao
interface DaoPost {
    @Query("SELECT * FROM post")
    fun getAll(): List<PostEntity>

    @Query("SELECT * FROM post WHERE id = :id")
    fun findById(id: Int): PostEntity

    @Insert
    fun insertPost(vararg todo: PostEntity)

    @Delete
    fun delete(todo: PostEntity)

    @Update
    fun updateTodo(vararg todos: PostEntity)


}
