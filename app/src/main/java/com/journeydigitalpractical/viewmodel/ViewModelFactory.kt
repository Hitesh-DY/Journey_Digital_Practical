package com.journeydigitalpractical.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.journeydigitalpractical.data.repository.PostsRepository

class ViewModelFactory constructor(private val repository: PostsRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            PostsViewModel(this.repository) as T
        }else if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            CommentsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}