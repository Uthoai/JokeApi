package com.free.unlimitedjokes.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.free.unlimitedjokes.R
import com.free.unlimitedjokes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        val categoriesList = arrayOf("Any", "Programming", "Miscellaneous", "Dark", "Pun", "Spooky", "Christmas")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoriesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        setListener()
        allObserver()

        return binding.root
    }

    private fun setListener() {
        binding.btnNext.setOnClickListener {
            val selectedCategory = binding.spinnerCategory.selectedItem.toString()
            viewModel.getRandomJokes(selectedCategory)
        }
    }

    private fun allObserver() {
        viewModel._responseJokesData.observe(viewLifecycleOwner){data->
            data?.let {
                binding.tvGenerateJokes.visibility = View.GONE
                if (data.joke == null){
                    viewModel.jokeSetup.postValue(data.setup ?: "")
                    viewModel.delivery.postValue(data.delivery ?: "")
                }else{
                    viewModel.jokeSetup.postValue("")
                    viewModel.delivery.postValue(data.joke)
                }
                viewModel.categories.postValue(data.category ?: "")
            }
        }
    }
}