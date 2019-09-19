package com.example.post.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.post.Entity.ComentarioEntity
import com.example.post.Entity.PostEntity

@Dao
interface DaoComentarios {
    @Query("SELECT * FROM comentario where postId = :id")
    fun getAll(id: Int): LiveData<List<ComentarioEntity>>

    @Insert
    fun insertPost(vararg todo: ComentarioEntity)

    @Delete
    fun delete(todo: ComentarioEntity)

    @Update
    fun updateTodo(vararg todos: ComentarioEntity)
}

