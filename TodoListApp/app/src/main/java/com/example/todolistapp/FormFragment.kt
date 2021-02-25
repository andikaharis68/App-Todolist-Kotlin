package com.example.todolistapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.databinding.FragmentFormBinding
import com.example.todolistapp.entity.Item
import java.util.*
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.enigmacamp.myviewmodel.ResourceStatus

class FormFragment : Fragment() {

    lateinit var sharedViewModel: MainActivityViewModel
    lateinit var viewModel: FormFragmentViewModel
    lateinit var binding: FragmentFormBinding
    lateinit var loadingDialog: AlertDialog
    var item : Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        loadingDialog = LoadingDialog.build(requireContext())
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.apply {
            txtShoppingDate.setInputType(InputType.TYPE_NULL)
            txtShoppingDate.setOnClickListener(View.OnClickListener {
                val datePickerDialog = activity?.let { it1 ->
                    DatePickerDialog(
                            it1, DatePickerDialog.OnDateSetListener
                    { view, year, monthOfYear, dayOfMonth ->
                        txtShoppingDate.setText(
                                "$year/$monthOfYear/$dayOfMonth",
                                TextView.BufferType.EDITABLE
                        );
                    }, year, month, day
                    )
                }
                datePickerDialog?.show()
            })
            buttonAdd.setOnClickListener {
                var quantity = Item.quantityCheck(txtQty.text)
                item = Item(txtShoppingDate.text.toString(), txtItemName.text.toString(), quantity, txtNotes.text.toString())
                viewModel.inputItemValidation(item!!)
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        sharedViewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return MainActivityViewModel(repo) as T
            }
        }).get(MainActivityViewModel::class.java)
        viewModel = ViewModelProvider(this).get(FormFragmentViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.isItemValid.observe(this) {
            when (it.status) {
                ResourceStatus.LOADING -> loadingDialog.show()
                ResourceStatus.SUCCESS -> {
                    loadingDialog.hide()
                    sharedViewModel.addItem(item!!)
                    findNavController().navigate(R.id.action_global_listFragment)
                    println("succes")}
                ResourceStatus.FAIL -> {
                    loadingDialog.hide()
                    Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                    ).show()
                println("fail")}
            }
        }
    }
}
