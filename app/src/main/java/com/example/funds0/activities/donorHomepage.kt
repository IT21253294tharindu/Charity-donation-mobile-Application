package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.funds0.LogIn
import com.example.funds0.R
import com.example.funds0.UserProfile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class donorHomepage : AppCompatActivity() {
    private lateinit var btnfund:Button
    private lateinit var profilepic:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_homepage)

        val user= Firebase.auth.currentUser

        btnfund=findViewById(R.id.btnfund)

        btnfund.setOnClickListener{
            val intent= Intent(this, DisplayfundsDonor::class.java)
            startActivity(intent)

        }

        profilepic=findViewById(R.id.Nimage01)
        profilepic.setOnClickListener {
            if (user==null){
                Toast.makeText(this,"You Are Not Logged In!!!", Toast.LENGTH_SHORT).show()
                val loginint=Intent(this, LogIn::class.java)
                startActivity(loginint)
            }
            val navprof=Intent(this, UserProfile::class.java)
            startActivity(navprof)
        }

    }
}