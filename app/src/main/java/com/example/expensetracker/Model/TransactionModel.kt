package com.example.expensetracker.Model

class TransactionModel {

    var id : Int = 0
    var amt : Int = 0
    lateinit var category :String
    lateinit var note:String
    var isExpense = 0

    constructor(id: Int, amt: Int, category: String, note: String, isExpense: Int) {
        this.id = id
        this.amt = amt
        this.category = category
        this.note = note
        this.isExpense = isExpense
    }
}

