package com.example.post.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.post.Database.PostRoomDatabase
import com.example.post.Entity.CommentEntity
import com.example.post.Repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CommentRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPost: LiveData<List<CommentEntity>>

    init {
        val commentDao = PostRoomDatabase.getDatabase(application, viewModelScope).commentDao()
        repository = CommentRepository(commentDao)
        allPost = repository.allPost
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(comment: CommentEntity) = viewModelScope.launch {
        repository.insert(comment)
    }
}