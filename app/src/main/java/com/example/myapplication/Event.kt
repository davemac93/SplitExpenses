package com.example.myapplication

data class Event(
    val id: String = "",
    val name: String = "",
    val date: String = "",
    val description: String = "",
    val participants: List<String> = listOf()
)