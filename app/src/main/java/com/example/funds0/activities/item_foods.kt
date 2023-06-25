package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R

class item_foods : AppCompatActivity() {
    private lateinit var btnNext: Button
    private lateinit var yfname: EditText
    private lateinit var yftype: EditText
    private lateinit var yfquantity: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_foods)

        yfname=findViewById(R.id.yfname)
        yftype=findViewById(R.id.yftype)
        yfquantity=findViewById(R.id.yfquantity)

        val clothename=intent.getStringExtra("clothename")
        val clothetype=intent.getStringExtra("clothetype")
        val clothequantity=intent.getStringExtra("clothequantity")
        val medicalname=  intent.getStringExtra("medicalname")
        val medicaltype=intent.getStringExtra("medicaltype")
        val medicalquantity= intent.getStringExtra("medicalquantity")


        btnNext=findViewById(R.id.btnNext)

        btnNext.setOnClickListener{
            val intent= Intent(this, item_education::class.java)
            intent.putExtra("clothename",clothename)
            intent.putExtra("clothetype",clothetype)
            intent.putExtra("clothequantity",clothequantity)
            intent.putExtra("medicalname",medicalname)
            intent.putExtra("medicaltype",medicaltype)
            intent.putExtra("medicalquantity",medicalquantity)
            intent.putExtra("foodname",yfname.text.toString())
            intent.putExtra("foodtype",yftype.text.toString())
            intent.putExtra("foodquantity",yfquantity.text.toString())


            startActivity(intent)
        }
    }
}