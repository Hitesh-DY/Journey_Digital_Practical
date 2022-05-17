package com.journeydigitalpractical.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.model.Posts

/**
 * Posts Dao class for perform local db table operations on posts & comments table
 */
@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<Posts>

    @Insert
    suspend fun insertAllPosts(posts: List<Posts>)

    @Insert
    suspend fun insertAllCommentsWithPost(posts: List<Comments>)

    @Query("DELETE FROM comments WHERE postId = :postId")
    suspend fun deleteCommentsOfPost(postId : Int)

    @Query("SELECT * FROM comments WHERE postId = :postId")
    suspend fun getCommentsOfPost(postId : Int): List<Comments>

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()

}