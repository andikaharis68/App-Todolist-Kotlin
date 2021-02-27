package com.example.todolistapp.presentation.item.update

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
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentUpdateBinding
import com.example.todolistapp.entity.Item
import com.example.todolistapp.repositories.ItemRepository

class UpdateFragment : Fragment() {
    private var itemUpdate: Item? = null
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemUpdate = it.getParcelable<Item>("item_update")
        }
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater)
        binding.apply {
            buttonCancel.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }
            itemUpdate?.apply {
                txtDateUpdate.setText(date)
                txtItemNameUpdate.setText(name)
                txtQtyUpdate.setText(quantity.toString())
                txtNotesUpdate.setText(note)
                buttonUpdate.setOnClickListener {
                    val updatedItem = copy(
                        date = txtDateUpdate.text.toString(),
                        name = txtItemNameUpdate.text.toString(),
                        quantity = txtQtyUpdate.text.toString().toInt(),
                        note = txtNotesUpdate.text.toString()
                    )
                    Log.d("Repo", updatedItem.toString())
                    viewModel.onUpdate(updatedItem)
                }
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ItemRepository()
                return UpdateViewModel(repository) as T
            }
        }).get(UpdateViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.updateStatus.observe(this) {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UpdateFragment()
    }
}