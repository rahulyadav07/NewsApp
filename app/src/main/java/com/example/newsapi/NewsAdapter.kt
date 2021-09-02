package com.example.newsapi

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


const val urls = "url"
class NewsAdapter(var context: Context, var Articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = Articles[position]
        holder.title.text = article.title
        holder.descriptions.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.image)
        holder.itemView.setOnClickListener{
           var intent = Intent(context,WebActivity::class.java)
            intent.putExtra(urls,article.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Articles.size
    }

    class ArticleViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ImageView>(R.id.imageView)
        val title = itemview.findViewById<TextView>(R.id.idtiltle)
        val descriptions = itemview.findViewById<TextView>(R.id.iddescription)
    }
}