package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R
import com.example.funds0.activities.item_medical

class item_clothes : AppCompatActivity() {
    private lateinit var btnNext: Button
    private lateinit var yname: EditText
    private lateinit var ytype: EditText
    private lateinit var yquantity: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_clothes)
        btnNext=findViewById(R.id.btnNext)
        ytype=findViewById(R.id.ytype)
        yname=findViewById(R.id.yname)
        yquantity=findViewById(R.id.yquantity)




        btnNext.setOnClickListener{
            val intent= Intent(this,item_medical::class.java)
            intent.putExtra("clothename",yname.text.toString())
            intent.putExtra("clothetype",ytype.text.toString())
            intent.putExtra("clothequantity",yquantity.text.toString())
            startActivity(intent)
        }

    }
}