package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.funds0.R

class bankinfo : AppCompatActivity() {
    private lateinit var Nacname:EditText
    private lateinit var Nacno:EditText
    private lateinit var Nbankname:EditText
    private lateinit var Nbtn_done:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bankinfo)

        Nacname=findViewById(R.id.Nacname)
        Nacno=findViewById(R.id.Nacno)
        Nbankname=findViewById(R.id.Nbankname)
        Nbtn_done=findViewById(R.id.Nbtn_done)




        Nbtn_done.setOnClickListener {
            val acname=Nacname.text.toString()
            val acno=Nacno.text.toString()
            val bankname=Nbankname.text.toString()
            if(acname.isEmpty()){
                Nacname.error=("please enter account name")
            }
            if(acno.isEmpty()){
                Nacno.error=("please enter account no")
            }
            if(bankname.isEmpty()){
                Nbankname.error=("please enter bank name")
            }
            else {
                val intent = Intent(this, activity_clothes::class.java)
                intent.putExtra("accountname", Nacname.text.toString())
                intent.putExtra("accountno", Nacno.text.toString())
                intent.putExtra("bankname", Nbankname.text.toString())

                startActivity(intent)
            }
        }



    }
}