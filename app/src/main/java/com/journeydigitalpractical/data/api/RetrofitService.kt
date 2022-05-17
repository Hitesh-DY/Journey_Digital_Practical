package com.journeydigitalpractical.data.api

import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.model.Posts
import com.journeydigitalpractical.utils.Utils
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * RetrofitService
 */
interface RetrofitService {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<Posts>>

    @GET("comments")
    suspend fun getAllCommentsOfPost(@Query("postId") postId : Int): Response<List<Comments>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}