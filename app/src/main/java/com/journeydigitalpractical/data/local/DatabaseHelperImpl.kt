package com.journeydigitalpractical.data.local
import com.journeydigitalpractical.data.model.Comments
import com.journeydigitalpractical.data.model.Posts

/**
 * This class for the database queries implementation
 * A suspending function is simply a function that can be paused and resumed at a later time. They can execute a long running operation
 */
class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getPosts(): List<Posts> = appDatabase.postsDao().getAllPosts()
    override suspend fun insertAll(posts: List<Posts>) = appDatabase.postsDao().insertAllPosts(posts)
    override suspend fun deleteAllPosts() = appDatabase.postsDao().deleteAllPosts()
    override suspend fun deleteCommentsOfPost(postId : Int) = appDatabase.postsDao().deleteCommentsOfPost(postId)
    override suspend fun getCommentsOfPost(postId: Int) : List<Comments> = appDatabase.postsDao().getCommentsOfPost(postId)
    override suspend fun insertAllCommentsWithPost(comments: List<Comments>) = appDatabase.postsDao().insertAllCommentsWithPost(comments)
}