package com.example.funds0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funds0.activities.donorHomepage

class charityCauses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity_causes)

        val toHome=findViewById<Button>(R.id.seeOrgBtn)
        toHome.setOnClickListener {
            val home= Intent(this, donorHomepage::class.java)
            startActivity(home)
        }
    }
}