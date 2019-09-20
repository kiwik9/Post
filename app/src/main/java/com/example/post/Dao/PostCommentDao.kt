package com.example.post.Dao
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.example.post.Entity.CommentEntity
import com.example.post.Entity.PostCommentEntity

@Dao
interface PostCommentDao {
    @Insert
    fun insert(postcommen: PostCommentEntity)

    @Query("""
           SELECT * FROM comment_table
           INNER JOIN post_comment_join
           ON comment_table.id=post_comment_join.commentId
           WHERE post_comment_join.commentId=:postId
           """)
    fun getCommentsByPost(postId: Int): Array<CommentEntity>
}