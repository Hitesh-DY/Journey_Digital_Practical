package com.journeydigitalpractical.ui.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.journeydigitalpractical.R
import com.journeydigitalpractical.data.model.Posts
import kotlinx.android.synthetic.main.single_posts_layout.view.*

/**
 * Single post list adapter
 */
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var postList = mutableListOf<Posts>()
    var matchedPosts = mutableListOf<Posts>()
    var onItemClick: ((Posts) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(postList: List<Posts>){
        this.postList = postList.toMutableList()
        this.matchedPosts = postList.toMutableList()
        notifyDataSetChanged()
    }

    // Filter list with body & title parameters in post list
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(searchQuery : String){
        if(this.postList.size > 0){
            this.matchedPosts = this.postList.filter { it.title.lowercase().contains(searchQuery.lowercase()) } as MutableList<Posts>
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder  = PostsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.single_posts_layout, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = holder.bind(matchedPosts[position])

    override fun getItemCount()= matchedPosts.size

    /**
     * Single post design view holder
     */
    inner class PostsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(matchedPosts[adapterPosition])
            }
        }
        fun bind(posts: Posts){
            itemView.postTitle.text = posts.title
            itemView.postDescription.text = posts.body
        }

    }

}


