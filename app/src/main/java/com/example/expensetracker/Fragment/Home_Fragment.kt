package com.example.expensetracker.Fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.Adapter.TransAdapter
import com.example.expensetracker.Database.DBHelper
import com.example.expensetracker.Model.TransactionModel
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.databinding.UpdateItemBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry



class Home_Fragment : Fragment() {

    lateinit var adapter: TransAdapter
    lateinit var dbHelper: DBHelper
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        updatedashboard()

        dbHelper = DBHelper(context)
        var transList = dbHelper.getTransaction()
        var totalIncome = 0
        var totalExpense = 0

        for (trans in transList) {

            if (trans.isExpense == 0) {
                totalIncome += trans.amt
            } else if (trans.isExpense == 1) {
                totalExpense += trans.amt
            }

        }

        var overall = (totalIncome - totalExpense).toString()


        binding.chart.setUsePercentValues(true);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.setExtraOffsets(5F, 10F, 5F, 5F);

        binding.chart.setDragDecelerationFrictionCoef(0.95f);

//        binding.chart.setCenterTextTypeface(tfLight);
        binding.chart.setCenterText(overall);

        binding.chart.setDrawHoleEnabled(true);
        binding.chart.setHoleColor(Color.WHITE);

        binding.chart.setTransparentCircleColor(Color.BLACK);
        binding.chart.setTransparentCircleAlpha(110);

        binding.chart.setHoleRadius(58f);
        binding.chart.setTransparentCircleRadius(61f);

        binding.chart.setDrawCenterText(true);

        binding.chart.setRotationAngle(0F);
        // enable rotation of the chart by touch
        binding.chart.setRotationEnabled(true);
        binding.chart.setHighlightPerTapEnabled(true);


        // add a selection listener
        val colors = ArrayList<Int>()


        colors.add(Color.RED)
        colors.add(Color.GREEN)

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(totalExpense.toFloat(), totalExpense.toString()))
        entries.add(PieEntry(totalIncome.toFloat(), totalIncome.toString()))
        val dataSet = PieDataSet(entries, "Income")
        dataSet.setColors(colors)
        var pieData = PieData(dataSet)
        binding.chart.data = pieData

        return binding.root


    }
    fun updatedashboard(){

        binding = FragmentHomeBinding.inflate(layoutInflater)



        dbHelper = DBHelper(context)
        var transList = dbHelper.getTransaction()
        var totalIncome = 0
        var totalExpense = 0

        for (trans in transList) {

            if (trans.isExpense == 0) {
                totalIncome += trans.amt
            } else if (trans.isExpense == 1) {
                totalExpense += trans.amt
            }

        }

         var totalbalance = (totalIncome - totalExpense).toString()

        binding.budjet1.text=totalIncome.toString().toInt().toString()
        binding.budjet2.text=totalExpense.toString().toInt().toString()
        binding.totalbalance.text=totalbalance.toInt().toString()

    }

}