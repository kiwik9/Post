package com.example.post.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comentario")
data class ComentarioEntity(

    @PrimaryKey(autoGenerate = true)
    var idComentario: Int,

    @ColumnInfo(name = "postId") var postId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "body") var body: String

)