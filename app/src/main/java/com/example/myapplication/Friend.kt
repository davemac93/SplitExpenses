package com.example.myapplication

data class Friend(
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = ""
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}