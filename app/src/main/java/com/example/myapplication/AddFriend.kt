package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddFriend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        val buttonClick = findViewById<Button>(R.id.IdCancel)
        buttonClick.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }
    }
}