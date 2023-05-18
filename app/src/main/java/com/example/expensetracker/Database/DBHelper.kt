package com.example.expensetracker.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.expensetracker.Fragment.Home_Fragment
import com.example.expensetracker.Model.TransactionModel
import android.database.sqlite.SQLiteOpenHelper as SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Expense.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        var sql =
            "CREATE TABLE trans(id INTEGER PRIMARY KEY AUTOINCREMENT,amt INTEGER,category TEXT,note TEXT,isexpense INTEGER)"

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    fun addTrans(trans: TransactionModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amt", amt)
                put("category", category)
                put("note", note)
                put("isexpense", isExpense)
            }
        }
        db.insert("trans", null, values)
    }

    fun getTransaction(): ArrayList<TransactionModel> {
        var transList = ArrayList<TransactionModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM trans"
        var cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var amt = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpense = cursor.getInt(4)
            var data = TransactionModel(id, amt, category, note, isExpense)
            transList.add(data)
            cursor.moveToNext()
        }
        return transList
    }

    fun deleteTransaction(id:Int) {


        var db = writableDatabase
        var sql = "DELETE FROM trans WHERE id='$id'"

        db.execSQL(sql)
    }
    fun updateTransaction(trans: TransactionModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amt",amt)
                put("category",category)
                put("note",note)
                put("isexpense",isExpense)
            }
        }

        db.update("trans",values,"id=${trans.id}",null)

    }

}