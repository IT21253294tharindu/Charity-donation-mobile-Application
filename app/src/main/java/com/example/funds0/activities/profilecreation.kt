package com.example.funds0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funds0.charityCauses

class profilecreation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilecreation)

        val skipint=Intent(this, charityCauses::class.java)

        val skip=findViewById<Button>(R.id.skipBtn)
        skip.setOnClickListener {
            startActivity(skipint)
        }

        val save=findViewById<Button>(R.id.saveBtn)
        save.setOnClickListener {
            startActivity(skipint)
        }
    }
}