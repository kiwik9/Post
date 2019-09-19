package com.example.post.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity(
        @PrimaryKey var id : Int,
        @ColumnInfo(name = "userid")  var userid: Int,
        @ColumnInfo(name = "title")  var title: String,
        @ColumnInfo(name = "body")  var body: String
)