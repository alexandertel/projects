package com.example.proba.list

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
import com.example.proba.data.Article
import java.text.SimpleDateFormat
import java.util.*

class ArticlesAdapter(
    private val clickItem: (id: String) -> Unit
) : ListAdapter<Article, ArticlesAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvArticleName: TextView = itemView.findViewById(R.id.tv_article_name_list)
        val tvArticleNumber: TextView = itemView.findViewById(R.id.tv_article_number)
        val ivPreviewArticle: ImageView = itemView.findViewById(R.id.iv_article_small)
        val tvArticleDate: TextView = itemView.findViewById(R.id.tv_article_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val article = getItem(pos)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(article.publishedAt)
        val stringDate = date?.let { outputFormat.format(it) }.orEmpty()

        holder.tvArticleName.text = article.title
        holder.tvArticleDate.text = stringDate
        holder.tvArticleNumber.text = (pos + 1).toString()

        Glide.with(holder.itemView.context)
            .load(article.imageUrl)
            .centerCrop()
            .into(holder.ivPreviewArticle)

        holder.itemView.setOnClickListener {
            clickItem(article.id)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.publishedAt == newItem.publishedAt
    }
}
