package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.funds0.R

class viewFundDetails : AppCompatActivity() {
    private lateinit var clothesam:TextView
    private lateinit var medicalsam:TextView
    private lateinit var foodsam:TextView
    private lateinit var eduam:TextView
    private lateinit var nbtndonate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_fund_details)

        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bank=intent.getStringExtra("bankname")
        val clothesamount=intent.getStringExtra("clothesamount")
        val medicalsamount=intent.getStringExtra("medicalsamount")
        val foodsamount=intent.getStringExtra("foodsamount")
        val educationsamount=intent.getStringExtra("educationsamount")



        nbtndonate=findViewById(R.id.nbtndonate)
        nbtndonate.setOnClickListener{
            val intent= Intent(this, donate::class.java)
            intent.putExtra("accountname",accountname)
            intent.putExtra("accountno",accountno)
            intent.putExtra("bankname",bank)
            intent.putExtra("clothesamount",clothesamount)
            intent.putExtra("medicalsamount",medicalsamount)
            intent.putExtra("foodsamount",foodsamount)
            intent.putExtra("educationsamount",educationsamount)
            startActivity(intent)
        }
        initview()
        setvalueview()
    }
    private fun initview(){
        clothesam=findViewById(R.id.clothesam)
        medicalsam=findViewById(R.id.medicalsam)
        foodsam=findViewById(R.id.foodsam)
        eduam=findViewById(R.id.eduam)

    }
    private fun setvalueview(){
        clothesam.text=intent.getStringExtra("clothesamount")
        medicalsam.text=intent.getStringExtra("medicalsamount")
        foodsam.text=intent.getStringExtra("foodsamount")
        eduam.text=intent.getStringExtra("educationsamount")
    }
}
