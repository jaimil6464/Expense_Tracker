package com.example.expensetracker.Adapter

import android.view.View
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.example.expensetracker.Model.TransactionModel
import com.example.expensetracker.R

class MyAdapter (dataSet: ArrayList<TransactionModel>)
    : DragDropSwipeAdapter<TransactionModel, MyAdapter.ViewHolder>(dataSet) {

        class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
            val txtAmt: TextView = itemView.findViewById(R.id.txtAmt)
            val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
            val txtNote: TextView = itemView.findViewById(R.id.txtNote)
        }

        override fun getViewHolder(itemLayout: View) = MyAdapter.ViewHolder(itemLayout)

      override  fun onBindViewHolder(item: TransactionModel, viewHolder: MyAdapter.ViewHolder, position: Int) {
            // Here we update the contents of the view holder's views to reflect the item's data
            viewHolder.txtAmt.text = item.amt.toString()
            viewHolder.txtCategory.text = item.category.toString()
            viewHolder.txtNote.text = item.note.toString()


        }

    override fun getViewToTouchToStartDraggingItem(item: TransactionModel, viewHolder: MyAdapter.ViewHolder, position: Int): View? {
            // We return the view holder's view on which the user has to touch to drag the item
            return viewHolder.txtAmt
        }


    }
