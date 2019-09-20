package com.example.post.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.post.Database.PostRoomDatabase
import com.example.post.Entity.PostEntity
import com.example.post.Repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository

    val allPost: LiveData<List<PostEntity>>

    init {
        val postDao = PostRoomDatabase.getDatabase(application, viewModelScope).postDao()
        repository = PostRepository(postDao)
        allPost = repository.allPost
    }

    fun insert(post: PostEntity) = viewModelScope.launch {
        repository.insert(post)
    }
}