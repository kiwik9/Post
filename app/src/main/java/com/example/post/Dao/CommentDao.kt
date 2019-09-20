package com.example.post.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.post.Entity.CommentEntity


@Dao
interface CommentDao {

    @Query("SELECT * from comment_table")
    fun getAll(): LiveData<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comment: CommentEntity)

    @Query("DELETE FROM comment_table")
    suspend fun deleteAll()
}