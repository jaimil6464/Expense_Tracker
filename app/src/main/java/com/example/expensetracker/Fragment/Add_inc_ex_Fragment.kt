package com.example.expensetracker.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensetracker.Adapter.TransAdapter
import com.example.expensetracker.Database.DBHelper
import com.example.expensetracker.Model.TransactionModel
import com.example.expensetracker.databinding.FragmentAddIncExBinding
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup

class Add_inc_ex_Fragment : Fragment() {

    companion object {
        lateinit var database: DBHelper
        lateinit var adapter: TransAdapter
        var isexpense = 0
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentAddIncExBinding.inflate(layoutInflater)

        database = DBHelper(context)

        binding.groupChoices.setOnCheckedChangeListener(object :
            SingleSelectToggleGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {

                if (binding.choiceA.id == checkedId) {
                    binding.btnIncome.text = "Add Income"
                    isexpense = 0
                } else if (binding.choiceB.id == checkedId) {
                    binding.btnIncome.text = "Add Expense"
                    isexpense = 1


                }

            }

        })


        binding.btnIncome.setOnClickListener {

            //addtrans function use then
            var amt = binding.editAmt.text.toString().toInt()
            var cat = binding.editCart.text.toString()
            var note = binding.editNote.text.toString()

            var data = TransactionModel(0, amt, cat, note, isexpense)

// add trans then remove then text and new trans in this use
            database.addTrans(data)
            binding.editAmt.setText("")
            binding.editCart.setText("")
            binding.editNote.setText("")

        }


        return binding.root

    }
}