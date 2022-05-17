package com.journeydigitalpractical.ui.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.journeydigitalpractical.R
import com.journeydigitalpractical.data.api.RetrofitService
import com.journeydigitalpractical.data.repository.PostsRepository
import com.journeydigitalpractical.viewmodel.ViewModelFactory
import com.journeydigitalpractical.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*

/**
 * Activity for show list of posts
 */
class PostsActivity : AppCompatActivity() {

    lateinit var viewModel : PostsViewModel
    private val postsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        // set recyclerview adapter
        recyclerview.adapter = postsAdapter

        //Define the click of adapter item
        postsAdapter.onItemClick = { it ->
            val intent = Intent(this, PostDetailsActivity::class.java)
            intent.putExtra("post", it)
            startActivity(intent)
        }

        val retrofitService = RetrofitService.getInstance()
        val postsRepository = PostsRepository(retrofitService)

        performSearch()

        viewModel = ViewModelProvider(this, ViewModelFactory(postsRepository)).get(PostsViewModel::class.java)

        // observe the post lists once available load into adapter
        viewModel.postsList.observe(this) {
            postsAdapter.setPosts(it)
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
        viewModel.getAllPosts()
    }

    private fun performSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                postsAdapter.filterList(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                postsAdapter.filterList(newText!!)
                return true
            }
        })
    }

}