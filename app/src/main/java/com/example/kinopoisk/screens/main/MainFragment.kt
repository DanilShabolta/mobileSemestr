package com.example.kinopoisk.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMainBinding
import androidx.navigation.fragment.findNavController
import com.example.kinopoisk.models.movieItemModel


class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding ?= null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.getMovies()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { movie ->
            clickMovie(movie, findNavController())
        }


        try {
            viewModel.myMovies.observe(viewLifecycleOwner) { list ->
                list.body()?.let { moviesModel ->
                    adapter.setList(moviesModel.items)
                }
            }
        } catch (e: Exception){
            Log.e("MAIN Fragment", "${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
        }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun clickMovie(model: movieItemModel, navController: NavController) {
            if (model.nameRu != null) {
                val bundle = Bundle()
                bundle.putSerializable("movie", model)
                navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            } else {
                Log.e("MainFragment", "Movie name is null")
            }
        }
    }
}