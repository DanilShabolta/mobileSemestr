package com.example.kinopoisk.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentDetailBinding
import com.example.kinopoisk.models.movieItemModel

class DetailFragment : Fragment() {
    private var mBinding: FragmentDetailBinding ?= null
    private val binding get() = mBinding!!
    lateinit var currentMovie: movieItemModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as movieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        binding.tvTitle.text = currentMovie.nameRu
        binding.tvDate.text = currentMovie.year.toString()
        binding.tvDescription.text = currentMovie.description
        val genreNames = currentMovie.genres?.map { it.genre }
        val genres = genreNames?.joinToString(", ")
        binding.tvGenres.text = genres

        Glide.with(binding.imgDetail.context)
            .load(currentMovie.posterUrlPreview)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}