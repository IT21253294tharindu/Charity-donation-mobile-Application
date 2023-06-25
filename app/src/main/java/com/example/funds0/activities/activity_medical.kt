package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.funds0.R

class activity_medical : AppCompatActivity() {


    private lateinit var Nmedicalamount:EditText

    private lateinit var nbtn_back:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)

        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bankname=intent.getStringExtra("bankname")

        Nmedicalamount=findViewById(R.id.Nmedicalamount)
        nbtn_back=findViewById(R.id.nbtn_back)
        nbtn_back.setOnClickListener {
            val intent=Intent(this, activity_clothes::class.java)
            startActivity(intent)

        }

        val clothesamount=intent.getStringExtra("clothesamount")

        var Nbtn_next=findViewById<Button>(R.id.Nbtn_next)
        Nbtn_next.setOnClickListener {
            val intent=Intent(this, activity_foods::class.java)
            intent.putExtra("clothesamount",clothesamount)
            intent.putExtra("accountname",accountname)
            intent.putExtra("accountno",accountno)
            intent.putExtra("bankname",bankname)
            intent.putExtra("medicalsamount",Nmedicalamount.text.toString())
            startActivity(intent)
        }

    }


}