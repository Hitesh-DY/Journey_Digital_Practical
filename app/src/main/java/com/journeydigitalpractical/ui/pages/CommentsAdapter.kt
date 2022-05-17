package com.journeydigitalpractical.ui.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.journeydigitalpractical.R
import com.journeydigitalpractical.data.model.Comments
import kotlinx.android.synthetic.main.single_comment_layout.view.*

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var commentList = mutableListOf<Comments>()

    @SuppressLint("NotifyDataSetChanged")
    fun setComments(postList: List<Comments>){
        this.commentList = postList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder  = CommentsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.single_comment_layout, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) = holder.bind(commentList[position])

    override fun getItemCount()= commentList.size

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


