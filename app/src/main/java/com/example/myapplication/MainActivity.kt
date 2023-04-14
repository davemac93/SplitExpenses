package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.FriendsList)
        buttonClick.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }
        val buttonClick1 = findViewById<Button>(R.id.AddEvent)
        buttonClick1.setOnClickListener {
            val intent = Intent(this, NewEvent::class.java)
            startActivity(intent)
        }
        val buttonClick2 = findViewById<Button>(R.id.logout_button)
        buttonClick2.setOnClickListener {
            logout()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    fun logout() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        println("User logged out.")
    }


}


