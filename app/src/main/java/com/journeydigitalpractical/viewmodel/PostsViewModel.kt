package com.journeydigitalpractical.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.journeydigitalpractical.data.local.DatabaseHelper
import com.journeydigitalpractical.data.model.Posts
import com.journeydigitalpractical.data.repository.PostsRepository
import com.journeydigitalpractical.utils.Utils
import kotlinx.coroutines.*

class PostsViewModel(private val repository: PostsRepository, private val dbHelper: DatabaseHelper) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val postsList = MutableLiveData<List<Posts>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllPosts(context : Context) {
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            //check if internet is available call api, If not avail then get data from local database
            if(Utils.isOnline(context)){
                val response  = repository.getAllPosts()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        postsList.postValue(response.body())

                        //Store latest data in local database
                        response.body()?.let { dbHelper.deleteAllPosts() }
                        response.body()?.let { dbHelper.insertAll(it) }

                        loading.value = false
                    }else{
                        loading.value = false
                        onError("Error : ${response.message()} ")
                    }
                }
            }else{
                val response  = dbHelper.getPosts()
                withContext(Dispatchers.Main){
                    if(response.isNotEmpty()){
                        postsList.postValue(response)
                        loading.value = false
                    }else{
                        loading.value = false
                    }
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