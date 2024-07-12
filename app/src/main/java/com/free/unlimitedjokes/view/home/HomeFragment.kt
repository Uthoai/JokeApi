package com.free.unlimitedjokes.view.home

import androidx.fragment.app.viewModels
import com.free.unlimitedjokes.base.BaseFragment
import com.free.unlimitedjokes.databinding.FragmentHomeBinding
import com.free.unlimitedjokes.service.JokesClient

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel by viewModels<HomeViewModel>()
    override fun setListener() {

        binding.btnNext.setOnClickListener {
            viewModel.getRandomJokes()
        }

    }

    override fun allObserver() {
        viewModel._jokesData.observe(this){data->
            binding.joke.text = data.delivery.toString()
        }
    }
}