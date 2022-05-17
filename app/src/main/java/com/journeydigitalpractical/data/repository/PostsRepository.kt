package com.journeydigitalpractical.data.repository

import com.journeydigitalpractical.data.api.RetrofitService

class PostsRepository constructor(private val retrofitService: RetrofitService){

    suspend fun getAllPosts() = retrofitService.getAllPosts()

    suspend fun getAllCommentsOfPosts(postId:Int) = retrofitService.getAllCommentsOfPost(postId)
}