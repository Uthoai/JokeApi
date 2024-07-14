package com.free.unlimitedjokes.view.favourite

import androidx.fragment.app.viewModels
import com.free.unlimitedjokes.adapter.JokesAdapter
import com.free.unlimitedjokes.base.BaseFragment
import com.free.unlimitedjokes.databinding.FragmentFavouriteBinding
import com.free.unlimitedjokes.db.JokesData

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate),JokesAdapter.HandleUserClick {
    private val viewModel by viewModels<FavouriteViewModel>()
    private lateinit var adapter: JokesAdapter
    override fun setListener() {

        viewModel.getAllJokes.observe(viewLifecycleOwner){list->
            adapter = JokesAdapter(list,this)
            binding.recyclerViewJokes.adapter = adapter
        }

    }

    override fun onDeleteClick(jokesData: JokesData) {
        viewModel.deleteJokes(jokesData)
    }

    override fun allObserver() {
        //
    }
}