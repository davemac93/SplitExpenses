package com.example.myapplication
data class Expense(
    val id: String? = null,
    val description: String? = null,
    val amount: Double? = null,
    val category: String? = null,
    val paidBy: String? = ""
)