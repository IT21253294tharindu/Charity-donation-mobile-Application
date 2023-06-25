package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.funds0.R
import com.example.funds0.models.fundsmodel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class activity_education : AppCompatActivity() {
    private lateinit var Neducationamount: EditText
    private lateinit var dbRef: DatabaseReference
    private lateinit var Nbtn_done: Button
    private lateinit var nbtn_back: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        Neducationamount = findViewById(R.id.Neducationamount)
        Nbtn_done = findViewById(R.id.Nbtn_done)
        nbtn_back=findViewById(R.id.nbtn_back)
        dbRef=FirebaseDatabase.getInstance().getReference("funds")
        Nbtn_done.setOnClickListener {
            sendAlldata()
            val intent = Intent(this, request_history::class.java)
            startActivity(intent)
        }
        nbtn_back.setOnClickListener {
            val intent = Intent(this, activity_foods::class.java)
            startActivity(intent)
        }
    }

    private fun sendAlldata() {

        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bankname=intent.getStringExtra("bankname").toString()
        val clothesamount = intent.getStringExtra("clothesamount")
        val medicalsamount = intent.getStringExtra("medicalsamount")
        val foodsamount = intent.getStringExtra("foodsamount")
        val educationsamount = Neducationamount.text.toString()
        val fundid = dbRef.push().key!!

        val fund = fundsmodel(fundid,clothesamount,medicalsamount,foodsamount,educationsamount,accountname,accountno,bankname, )
        dbRef.child(fundid).setValue(fund).addOnCompleteListener {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            Neducationamount.text.clear()
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}