package com.journeydigitalpractical.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.journeydigitalpractical.data.local.DatabaseHelper
import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.repository.PostsRepository
import com.journeydigitalpractical.utils.Utils
import kotlinx.coroutines.*

class CommentsViewModel (private val repository: PostsRepository, private val dbHelper: DatabaseHelper) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val commentList = MutableLiveData<List<Comments>>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()


    fun getAllCommentsOfPost(postId:Int, context : Context) {
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)

            //check if internet is available call api, If not avail then get data from local database
            if(Utils.isOnline(context)){
                val response  = repository.getAllCommentsOfPosts(postId)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        commentList.postValue(response.body())

                        //Store latest data in local database
                        response.body()?.let { dbHelper.deleteCommentsOfPost(postId) }
                        response.body()?.let { dbHelper.insertAllCommentsWithPost(it) }
                        loading.value = false
                    }else{
                        loading.value = false
                        onError("Error : ${response.message()} ")
                    }
                }
            }else{
                val response  = dbHelper.getCommentsOfPost(postId)
                withContext(Dispatchers.Main){
                    if(response.isNotEmpty()){
                        commentList.postValue(response)
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