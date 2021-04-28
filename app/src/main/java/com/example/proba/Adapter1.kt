package com.example.proba

import android.R.attr.fragment
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class Adapter1(private val listArticles: List<Article>) : RecyclerView.Adapter<Adapter1.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var text1: TextView? = null

        val text1: TextView = itemView.findViewById(R.id.text_item)
        val text2: TextView = itemView.findViewById(R.id.text_item2)
        val pic1: ImageView = itemView.findViewById(R.id.image_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_item1, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val article = listArticles.get(pos)//getitem(pos)

        holder.text1.text = article.title
        holder.text2.text = "${pos + 1}"//(pos+1).toString()


        Glide.with(holder.itemView.context)
            .load(article.imageUrl)
            .centerCrop()
            .into(holder.pic1)

        //        if (chislo % 2 == 0) {
//            holder.text1.textSize = 100.01f
//        } else {
//            holder.text1.textSize = 50f
//        }
    }


    override fun getItemCount(): Int {
        return listArticles.size
    }

    fun getTitle(article: Article): String {
        return article.title
    }

    fun getitem(position: Int): Article {
        return listArticles.get(position)  //?:-1 перестраховка от сбоев
    }
}