package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R

import com.example.funds0.models.itemmodel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class item_education : AppCompatActivity() {
    private lateinit var btndone: Button
    private lateinit var yename: EditText
    private lateinit var yetype: EditText
    private lateinit var yequantity: EditText
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_education)

        yename=findViewById(R.id.yename)
        yetype=findViewById(R.id.yetype)
        yequantity=findViewById(R.id.yequantity)
        dbRef= FirebaseDatabase.getInstance().getReference("items")

        btndone=findViewById(R.id.btndone)
        btndone.setOnClickListener{

            savedata()
            val intent= Intent(this, item_requested_history::class.java)
            startActivity(intent)


    }
}
    private fun savedata(){
        val clothename=intent.getStringExtra("clothename")
        val clothetype=intent.getStringExtra("clothetype")
        val clothequantity=intent.getStringExtra("clothequantity")
        val medicalname=  intent.getStringExtra("medicalname")
        val medicaltype=intent.getStringExtra("medicaltype")
        val medicalquantity= intent.getStringExtra("medicalquantity")
        val foodname=intent.getStringExtra("foodname")
        val foodtype=intent.getStringExtra("foodtype")
        val foodquantity=intent.getStringExtra("foodquantity")
        val educationname=yename.text.toString()
        val educationtype=yetype.text.toString()
        val educationquantity=yequantity.text.toString()
        val itemid=dbRef.push().key!!

        val items= itemmodel(
            itemid,
            clothename,
            clothetype,
            clothequantity,
            medicalname,
            medicaltype,
            medicalquantity,
            foodname,
            foodtype,
            foodquantity,
            educationname,
            educationtype,
            educationquantity
        )
        dbRef.child(itemid).setValue(items).addOnCompleteListener{
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{err->
            Toast.makeText(this, "error ${err.message}", Toast.LENGTH_LONG).show()
        }


    }


}