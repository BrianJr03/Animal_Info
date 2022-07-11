package jr.brian.volley.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jr.brian.volley.model.remote.News
import jr.brian.volley.databinding.NewsViewBinding

class NewsAdapter(
    private var context: Context,
    private var news: ArrayList<News>
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var binding: NewsViewBinding

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = NewsViewBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.apply {
            val newsInfo = news[position]
            newsTitle.text = "Title: ${newsInfo.title}"
            newsDescr.text = "Descr: ${newsInfo.description}"
            newsAuthor.text = "Written By: ${newsInfo.author}"
            Glide.with(context)
                .load(newsInfo.image).into(imageview)
        }
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = binding.title
        val newsDescr: TextView = binding.description
        val newsAuthor: TextView = binding.author
        val imageview: AppCompatImageView = binding.newsImage
    }
}
