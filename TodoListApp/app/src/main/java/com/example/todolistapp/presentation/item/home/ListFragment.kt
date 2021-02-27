package com.example.todolistapp.presentation.item.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentListBinding
import com.example.todolistapp.presentation.main.MainActivityViewModel
import com.example.todolistapp.repositories.ItemRepository
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var rvAdapter: RecyclerAdapter
    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.loadItemData()
            rvAdapter = RecyclerAdapter(viewModel)
            recycler_view_item.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = rvAdapter
            }
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return MainActivityViewModel(repo) as T
            }
        }).get(MainActivityViewModel::class.java)
    }

    fun subscribe() {
        viewModel.itemLiveData.observe(this) {
            Log.d("subscribe","$it")
            rvAdapter.setData(it)
        }
        viewModel.itemUpdateLiveData.observe(this) {
            Navigation.findNavController(requireView())
                    .navigate(R.id.updateFragment, bundleOf("item_update" to it))
        }

    }
}