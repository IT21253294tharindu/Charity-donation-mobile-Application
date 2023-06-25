package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.funds0.R

class activity_foods : AppCompatActivity() {
    private lateinit var Nfoodsamount:EditText

    private lateinit var nbtn_back:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foods)

        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bankname=intent.getStringExtra("bankname")

         Nfoodsamount=findViewById(R.id.Nfoodsamount)
        nbtn_back=findViewById(R.id.nbtn_back)

        nbtn_back.setOnClickListener {
            val intent=Intent(this, activity_medical::class.java)
            startActivity(intent)
        }
        val clothesamount=intent.getStringExtra("clothesamount")
        val medicalamount=intent.getStringExtra("medicalsamount")

        var Nbtn_next=findViewById<Button>(R.id.Nbtn_next)
        Nbtn_next.setOnClickListener {
            val intent=Intent(this, activity_education::class.java)
            intent.putExtra("accountname",accountname)
            intent.putExtra("accountno",accountno)
            intent.putExtra("bankname",bankname)
            intent.putExtra("clothesamount",clothesamount)
            intent.putExtra("medicalsamount",medicalamount)
            intent.putExtra("foodsamount",Nfoodsamount.text.toString())
            startActivity(intent)
        }
    }

}