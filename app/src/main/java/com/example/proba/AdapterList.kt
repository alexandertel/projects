package com.example.proba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterList(  //   создаем класс
    private val listArticles: List<Article>,  // создаем приватное (видимое только в текущем файле) значение listArticles типа список Article
    private val clickItem: (id: String) -> Unit  // создаем постоянную clickItem типа Стринг функционального типа
) : RecyclerView.Adapter<AdapterList.MyViewHolder>() {  // наследуем класс RecyclerView.Adapter

//    fun clickItem(id: String): Unit {
//
//        id
//    } описание private val clickItem: (id: String) -> Unit

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {  // создаем класс вьюхолдера RecyclerView

        val tvArticleName: TextView = itemView.findViewById(R.id.tv_article_name_list)  // создаем значение  tvArticleName текствью с АйДи в макете
        val tvArticleNumber: TextView = itemView.findViewById(R.id.tv_article_number)   // еще один текст
        val ivPreviewArticle: ImageView = itemView.findViewById(R.id.iv_article_small)  // картинка
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {   // переопределяем фкнкцию создания вьюхолдера
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_list, parent, false)   // инфлатим вьюшку по макету adapter_list
        return MyViewHolder(itemView)// ключево слова для возврата значения из функциии а мименно  возвр обьект типа май вью холдер
    }

//    interface ArticleClickListener {
//        fun onClick(id: String)
//    }

//    var articleClickListener: ArticleClickListener? = null

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {   // вызываем информацию в заданном месте
        val article = listArticles.get(pos)   //getitem(pos)

        holder.tvArticleName.text = article.title      // задаем переменной типа текс значение title  из класа article
        holder.tvArticleNumber.text = "${pos + 1}"       //(pos+1).toString()

        Glide.with(holder.itemView.context)       // используем Glide для вставки картинок
            .load(article.imageUrl)
            .centerCrop()
            .into(holder.ivPreviewArticle)

        holder.itemView.setOnClickListener {
            clickItem(article.id)                                 // Делаем названия статей кликабельными
//            articleClickListener?.onClick(article.id)
        }

    }

    override fun getItemCount(): Int {
        return listArticles.size                         // выдает размер списка
    }

    fun getTitle(article: Article): String {
        return article.title                                // возвращает название статей
    }

    fun getitem(position: Int): Article {
        return listArticles.get(position)  //?:-1 перестраховка от сбоев
    }

}
