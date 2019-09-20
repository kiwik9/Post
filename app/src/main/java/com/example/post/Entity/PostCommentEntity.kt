package com.example.post.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "post_comment_join",
        primaryKeys = arrayOf("postId","commentId"),
        foreignKeys = arrayOf(
                ForeignKey(entity = PostEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("postId")),
                ForeignKey(entity = CommentEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("commentId"))
                            )
    )
data class PostCommentEntity(
        val postId : Int,
        val commentId : Int
)