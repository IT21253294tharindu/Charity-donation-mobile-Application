package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.funds0.R

class activity_clothes : AppCompatActivity() {
    private lateinit var Nclothesamount: EditText
    private lateinit var Ntotalamount:TextView

    private lateinit var nbtn_back:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bankname=intent.getStringExtra("bankname")

         Ntotalamount=findViewById(R.id.Nclothesamount)
        Nclothesamount=findViewById(R.id.Nclothesamount)

        nbtn_back=findViewById(R.id.nbtn_back)
        nbtn_back.setOnClickListener {
            val intent=Intent(this, bankinfo::class.java)
            startActivity(intent)
        }




        var Nbtn_next=findViewById<Button>(R.id.Nbtn_next)
        Nbtn_next.setOnClickListener {
            val intent=Intent(this, activity_medical::class.java)

            intent.putExtra("accountname",accountname)
            intent.putExtra("accountno",accountno)
            intent.putExtra("bankname",bankname)
            intent.putExtra("clothesamount",Nclothesamount.text.toString())

            startActivity(intent)
        }
    }

}


