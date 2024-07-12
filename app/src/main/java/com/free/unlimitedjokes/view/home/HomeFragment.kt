package com.free.unlimitedjokes.view.home

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.free.unlimitedjokes.base.BaseFragment
import com.free.unlimitedjokes.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun setListener() {
        val categories = arrayOf("Any", "Programming", "Miscellaneous", "Dark", "Pun", "Spooky", "Christmas")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        binding.btnNext.setOnClickListener {
            val selectedCategory = binding.spinnerCategory.selectedItem.toString()
            viewModel.getRandomJokes(selectedCategory)
        }

    }

    override fun allObserver() {
        viewModel._jokesData.observe(this){data->
            if (data.joke == null){
                binding.setup.text = data.setup ?: ""
                binding.joke.text = data.delivery
            }else{
                binding.joke.text = data.joke
            }
        }
    }
}