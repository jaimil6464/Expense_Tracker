package com.example.expensetracker.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.expensetracker.Fragment.Add_inc_ex_Fragment
import com.example.expensetracker.Fragment.Home_Fragment
import com.example.expensetracker.Fragment.TransactionFragment
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        supportFragmentManager
            .beginTransaction()
            .replace(R.id.view, Home_Fragment())
            .commit()


        binding.navBtn.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btn_home -> {
                    loadFragments(Home_Fragment())
                    true
                }


                R.id.btn_add -> {
                    loadFragments(Add_inc_ex_Fragment())
                    true
                }

                R.id.btn_list -> {
                    loadFragments(TransactionFragment())
                    true
                }


                else -> {
                    true
                }
            }
        }

    }


    private fun loadFragments(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.view, fragment)
            .commit()

    }
}



