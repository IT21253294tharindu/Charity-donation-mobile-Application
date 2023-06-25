package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.funds0.R
import com.example.funds0.models.donatemodel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class donate : AppCompatActivity() {
    private lateinit var an:TextView
    private lateinit var anoo:TextView
    private lateinit var bn:TextView
    private lateinit var nbtnconfirm:Button
    private lateinit var nbtncancel:Button
    private lateinit var dbRef:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        an=findViewById(R.id.an)
        anoo=findViewById(R.id.anoo)
        bn=findViewById(R.id.bn)
        nbtnconfirm=findViewById(R.id.nbtnconfirm)
        nbtncancel=findViewById(R.id.nbtncancel)
        dbRef=FirebaseDatabase.getInstance().getReference("donates")
        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bank=intent.getStringExtra("bankname")
        val clothesamount=intent.getStringExtra("clothesamount")
        val medicalsamount=intent.getStringExtra("medicalsamount")
        val foodsamount=intent.getStringExtra("foodsamount")
        val educationsamount=intent.getStringExtra("educationsamount")

        setvalueview()
        nbtnconfirm.setOnClickListener {
            sendata()
            val intent = Intent(this, donatedhistory::class.java)
            startActivity(intent)
        }
         nbtncancel.setOnClickListener{
             val intent=Intent(this, DisplayfundsDonor::class.java)
             startActivity(intent)
         }




    }
    private fun setvalueview(){
        an.text=intent.getStringExtra("accountname")
        anoo.text=intent.getStringExtra("accountno")
        bn.text=intent.getStringExtra("bankname")

    }
    private fun sendata(){
        val accountname=intent.getStringExtra("accountname")
        val accountno=intent.getStringExtra("accountno")
        val bank=intent.getStringExtra("bankname")
        val clothesamount=intent.getStringExtra("clothesamount")
        val medicalsamount=intent.getStringExtra("medicalsamount")
        val foodsamount=intent.getStringExtra("foodsamount")
        val educationsamount=intent.getStringExtra("educationsamount")
        val donid=dbRef.push().key!!

        val donates= donatemodel(donid,clothesamount,medicalsamount,foodsamount,educationsamount,accountname,accountno,bank)
        dbRef.child(donid).setValue(donates).addOnCompleteListener{
            Toast.makeText(this,"donation completed",Toast.LENGTH_LONG).show()

        }.addOnFailureListener{err->
            Toast.makeText(this,"Error $err",Toast.LENGTH_LONG).show()

        }


    }
}