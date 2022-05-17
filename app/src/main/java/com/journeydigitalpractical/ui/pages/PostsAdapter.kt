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
    var onItemClick: ((Posts) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(postList: List<Posts>){
        this.postList = postList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder  = PostsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.single_posts_layout, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = holder.bind(postList[position])

    override fun getItemCount()= postList.size

    /**
     * Single post design view holder
     */
    inner class PostsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(postList[adapterPosition])
            }
        }
        fun bind(posts: Posts){
            itemView.postTitle.text = posts.title
            itemView.postDescription.text = posts.body
        }

    }

}


