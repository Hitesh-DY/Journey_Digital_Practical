package com.journeydigitalpractical.ui.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.journeydigitalpractical.R
import com.journeydigitalpractical.data.model.Comments
import kotlinx.android.synthetic.main.item_comment_layout.view.*

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var commentList = mutableListOf<Comments>()
    var matchedCommentList = mutableListOf<Comments>()

    @SuppressLint("NotifyDataSetChanged")
    fun setComments(postList: List<Comments>){
        this.commentList = postList.toMutableList()
        this.matchedCommentList = postList.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterComments(searchQuery : String){
        if(this.commentList.size > 0){
            this.matchedCommentList = this.commentList.filter { it.body.lowercase().contains(searchQuery.lowercase())
                    || it.email.lowercase().contains(searchQuery.lowercase())
                    || it.name.lowercase().contains(searchQuery.lowercase()) } as MutableList<Comments>
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder  = CommentsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_comment_layout, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) = holder.bind(matchedCommentList[position])

    override fun getItemCount()= matchedCommentList.size

    /**
     * Single comment design view holder
     */
    inner class CommentsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comments: Comments){
            itemView.commentUserEmail.text = comments.email
            itemView.commentUserName.text = comments.name
            itemView.commentDescription.text = comments.body
        }

    }

}


