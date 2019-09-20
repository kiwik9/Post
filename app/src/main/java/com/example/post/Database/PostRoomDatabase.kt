package com.example.post.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.post.Dao.CommentDao
import com.example.post.Dao.PostDao
import com.example.post.Entity.CommentEntity
import com.example.post.Entity.PostEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class PostRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var INSTANCE: PostRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): PostRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostRoomDatabase::class.java,
                        "´post_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(PostDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class PostDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                    }
                }
            }
        }

        suspend fun populateDatabase(postDao: PostDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            postDao.deleteAll()

            var post = PostEntity(1,1,"Lol","lol2")
            postDao.insert(post)
        }
    }

}
