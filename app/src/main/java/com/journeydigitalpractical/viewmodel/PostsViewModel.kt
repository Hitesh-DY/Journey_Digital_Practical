package com.journeydigitalpractical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.journeydigitalpractical.data.model.Posts
import com.journeydigitalpractical.data.repository.PostsRepository
import kotlinx.coroutines.*

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val postsList = MutableLiveData<List<Posts>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllPosts() {
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response  = repository.getAllPosts()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    postsList.postValue(response.body())
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