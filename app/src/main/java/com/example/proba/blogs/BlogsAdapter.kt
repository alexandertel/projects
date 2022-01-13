package com.example.proba.blogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proba.R
import com.example.proba.data.Blog
import java.text.SimpleDateFormat
import java.util.*

class BlogsAdapter(
    private val clickItem: (id: String) -> Unit
) : ListAdapter<Blog, BlogsAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvBlogName: TextView = itemView.findViewById(R.id.tv_blog_title)
        val tvBlogNumber: TextView = itemView.findViewById(R.id.tv_blog_number)
        val ivPreviewBlog: ImageView = itemView.findViewById(R.id.iv_blog_pic)
        val tvBlogDate: TextView = itemView.findViewById(R.id.tv_blog_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_blog, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val blog = getItem(pos)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(blog.publishedAt)
        val stringDate = date?.let { outputFormat.format(it) }.orEmpty()

        holder.tvBlogName.text = blog.title
        holder.tvBlogDate.text = stringDate
        holder.tvBlogNumber.text = (pos + 1).toString()

        Glide.with(holder.itemView.context)
            .load(blog.imageUrl)
            .centerCrop()
            .into(holder.ivPreviewBlog)

        holder.itemView.setOnClickListener {
            clickItem(blog.id)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.publishedAt == newItem.publishedAt
    }
}
