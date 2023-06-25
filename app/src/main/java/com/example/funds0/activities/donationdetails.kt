package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.funds0.R

class donationdetails : AppCompatActivity() {

    private lateinit var Naname: TextView
    private lateinit var Nano: TextView
    private lateinit var Nbname: TextView
    private lateinit var Ncamount: TextView
    private lateinit var Nmamount: TextView
    private lateinit var Nfamount: TextView
    private lateinit var Neduamount: TextView
    private lateinit var btncancel: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donationdetails)
        btncancel=findViewById(R.id.btncancel)
        btncancel.setOnClickListener {
            val intent = Intent(this, donatedhistory::class.java)
            startActivity(intent)
        }
        initview()
        setValuesToViews()

    }

    private fun initview() {
        Naname = findViewById(R.id.Naname)
        Nano = findViewById(R.id.Nano)
        Nbname = findViewById(R.id.Nbname)
        Ncamount = findViewById(R.id.Ncamount)
        Nmamount = findViewById(R.id.Nmamount)
        Nfamount = findViewById(R.id.Nfamount)
        Neduamount = findViewById(R.id.Neduamount)

    }

    private fun setValuesToViews() {
        Naname.text = intent.getStringExtra("accountname")
        Nano.text = intent.getStringExtra("accountno")
        Nbname.text = intent.getStringExtra("bankname")
        Ncamount.text = intent.getStringExtra("clothesamount")
        Nmamount.text = intent.getStringExtra("medicalsamount")
        Nfamount.text = intent.getStringExtra("foodsamount")
        Neduamount.text = intent.getStringExtra("educationsamount")
    }
}