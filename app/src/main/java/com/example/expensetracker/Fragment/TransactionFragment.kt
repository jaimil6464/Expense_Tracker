package com.example.expensetracker.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.Adapter.TransAdapter
import com.example.expensetracker.Database.DBHelper
import com.example.expensetracker.Model.TransactionModel
import com.example.expensetracker.databinding.FragmentTransactionBinding
import com.example.expensetracker.databinding.UpdateItemBinding


class TransactionFragment : Fragment() {

    lateinit var binding: FragmentTransactionBinding
    lateinit var adapter: TransAdapter
    lateinit var dbHelper: DBHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        dbHelper = DBHelper(context)
        adapter = TransAdapter({
            dbHelper.deleteTransaction(it)
            adapter.update(dbHelper.getTransaction())
        }, {
            var dialog = Dialog(requireContext())
            var bind = UpdateItemBinding.inflate(layoutInflater)
            dialog.setContentView(bind.root)

            it.apply {
                bind.apply {
                    editAmt.setText(amt.toString())
                    editCart.setText(category)
                    editNote.setText(note)

                    btnUpdate.setOnClickListener {
                        var amt2 = editAmt.text.toString().toInt()
                        var category2 = editCart.text.toString()
                        var note2 = editNote.text.toString()

                        var model = TransactionModel(id,amt2,category2,note2,isExpense)

                        dbHelper.updateTransaction(model)
                        adapter.update(dbHelper.getTransaction())
                        dialog.dismiss()
                    }
                }
            }

            dialog.show()



        })
        adapter.Trans(dbHelper.getTransaction())

        binding.rcvTrans.layoutManager = LinearLayoutManager(context)
        binding.rcvTrans.adapter = adapter



        return binding.root
    }
}