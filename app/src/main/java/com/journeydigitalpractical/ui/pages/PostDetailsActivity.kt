package com.journeydigitalpractical.ui.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.journeydigitalpractical.R
import com.journeydigitalpractical.data.api.RetrofitService
import com.journeydigitalpractical.data.model.Posts
import com.journeydigitalpractical.data.repository.PostsRepository
import com.journeydigitalpractical.viewmodel.CommentsViewModel
import com.journeydigitalpractical.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.activity_post_details.progressDialog
import kotlinx.android.synthetic.main.activity_posts.*

/**
 * This activity for show the detail & comments of Post
 */
class PostDetailsActivity : AppCompatActivity() {

    lateinit var viewModel : CommentsViewModel
    private val commentsAdapter = CommentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val data = intent.extras
        val posts = data?.getParcelable<Posts>("post")
        val id = posts?.id

        // set recyclerview adapter
        recyclerviewComments.adapter = commentsAdapter

        val retrofitService = RetrofitService.getInstance()
        val postsRepository = PostsRepository(retrofitService)

        viewModel = ViewModelProvider(this, ViewModelFactory(postsRepository)).get(CommentsViewModel::class.java)


        // observe the post lists once available load into adapter
        viewModel.commentList.observe(this) {
            commentsAdapter.setComments(it)
        }

        // observe the error message
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                progressDialog.visibility = View.VISIBLE
            } else {
                progressDialog.visibility = View.GONE
            }
        })

        // call Api to get post lists
        viewModel.getAllCommentsOfPost(id!!)


    }

}