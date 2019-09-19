package com.example.post.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post")
data class PostEntity(

    @PrimaryKey var id: Int,
    @ColumnInfo(name = "userId") var userId: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "body") var body: String

)