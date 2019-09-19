package com.example.post.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.post.DAO.DaoComentarios
import com.example.post.DAO.DaoPost
import com.example.post.Entity.ComentarioEntity
import com.example.post.Entity.PostEntity

@Database(
    entities = [PostEntity::class, ComentarioEntity::class],
    version = 1
)
abstract class AppDB : RoomDatabase(){
    abstract fun PostDao(): DaoPost
    abstract fun ComentarioDao(): DaoComentarios

    companion object {
        @Volatile private var instance: AppDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDB::class.java, "posts.db")
            .build()
    }
}

