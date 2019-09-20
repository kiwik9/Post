package com.example.post.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.post.Entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * from post_table")
    fun getAll(): LiveData<List<PostEntity>>

    @Insert
    fun insert(post: PostEntity)

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()
}
