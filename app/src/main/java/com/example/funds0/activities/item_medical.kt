package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R


class item_medical : AppCompatActivity() {
    private lateinit var btnNext: Button
    private lateinit var ymname: EditText
    private lateinit var ymtype: EditText
    private lateinit var ymquantity: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_medical)

        ymname=findViewById(R.id.ymname)
        ymtype=findViewById(R.id.ymtype)
        ymquantity=findViewById(R.id.ymquantity)

        val clothename=intent.getStringExtra("clothename")
        val clothetype=intent.getStringExtra("clothetype")
        val clothequantity=intent.getStringExtra("clothequantity")



        btnNext=findViewById(R.id.btnNext)

        btnNext.setOnClickListener{
            intent= Intent(this, item_foods::class.java)
            intent.putExtra("clothename",clothename)
            intent.putExtra("clothetype",clothetype)
            intent.putExtra("clothequantity",clothequantity)
            intent.putExtra("medicalname",ymname.text.toString())
            intent.putExtra("medicaltype",ymtype.text.toString())
            intent.putExtra("medicalquantity",ymquantity.text.toString())


            startActivity(intent)
        }
    }
}