package com.journeydigitalpractical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.repository.PostsRepository
import kotlinx.coroutines.*

class CommentsViewModel (private val repository: PostsRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val commentList = MutableLiveData<List<Comments>>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()


    fun getAllCommentsOfPost(postId:Int) {
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response  = repository.getAllCommentsOfPosts(postId)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    commentList.postValue(response.body())
                    loading.value = false
                }else{
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}