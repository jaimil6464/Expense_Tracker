package com.example.expensetracker.Adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.Model.TransactionModel
import com.example.expensetracker.R
import com.example.expensetracker.databinding.TransItemBinding

class TransAdapter(delete: (Int) -> Unit, update: (TransactionModel) -> Unit) :
    RecyclerView.Adapter<TransAdapter.TransHolder>() {
    lateinit var transaction: ArrayList<TransactionModel>
    var deleteClick = delete
    var updateClick = update

    class TransHolder(itemView: TransItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        var binding = TransItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransHolder(binding)
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    override fun onBindViewHolder(
        holder: TransHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.apply {
            txtAmt.text = transaction.get(position).amt.toString()
            txtCategory.text = transaction.get(position).category
            txtNote.text = transaction.get(position).note

            if (transaction.get(position).isExpense == 0) {
                txtAmt.setTextColor(Color.GREEN)
            } else {
                txtAmt.setTextColor(Color.RED)
            }

        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                var option = PopupMenu(holder.itemView.context, holder.itemView)
                option.menuInflater.inflate(R.menu.list_popup, option.menu)

                option.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {

                        when (item?.itemId) {
                            R.id.delete -> {
                                deleteClick.invoke(transaction.get(position).id)
                            }

                            R.id.update -> {
                                updateClick.invoke(transaction.get(position))
                            }
                        }

                        return false
                    }

                })

                option.show()

                return true
            }

        })
    }


    fun Trans(transaction: ArrayList<TransactionModel>) {
        this.transaction = transaction
    }

    fun update(transaction: java.util.ArrayList<TransactionModel>) {
        this.transaction = transaction
        notifyDataSetChanged()
    }
}