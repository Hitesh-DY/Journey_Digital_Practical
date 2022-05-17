package com.journeydigitalpractical.data.local

import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.model.Posts

/**
 * Database helper class for crate contract for Database helper implementation.
 */
interface DatabaseHelper {
    suspend fun getPosts(): List<Posts>
    suspend fun insertAll(posts: List<Posts>)
    suspend fun deleteAllPosts()
    suspend fun deleteCommentsOfPost(postId : Int)
    suspend fun getCommentsOfPost(postId : Int): List<Comments>
    suspend fun insertAllCommentsWithPost(comments: List<Comments>)
}