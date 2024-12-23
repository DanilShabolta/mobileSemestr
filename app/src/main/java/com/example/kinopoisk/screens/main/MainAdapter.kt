package com.example.kinopoisk.screens.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.models.movieItemModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var listMovies = emptyList<movieItemModel>()
    private var listener: ((movieItemModel) -> Unit)? = null

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.item_title)
        private val date: TextView = view.findViewById(R.id.item_date)
        private val poster: ImageView = view.findViewById(R.id.item_img)
        private val itemDescription: TextView = view.findViewById(R.id.item_description)

        fun bind(movie: movieItemModel) {
            title.text = movie.nameRu
            date.text = movie.year.toString()
            itemDescription.text = movie.description

            Glide.with(itemView.context)
                .load(movie.posterUrlPreview)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(poster)
        }
    }

    fun setOnItemClickListener(listener: (movieItemModel) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            if (movie.nameRu != null) {
                listener?.invoke(movie)
            } else {
                Log.e("MainAdapter", "Movie name is null")
            }
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<movieItemModel>) {
        listMovies = list
        notifyDataSetChanged()
    }
}