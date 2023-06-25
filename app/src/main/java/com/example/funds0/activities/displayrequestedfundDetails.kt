package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.funds0.R
import com.example.funds0.models.fundsmodel
import com.google.firebase.database.FirebaseDatabase

class displayrequestedfundDetails : AppCompatActivity() {
    private lateinit var Naname:TextView
    private lateinit var Nano:TextView
    private lateinit var Nbname:TextView
    private lateinit var Ncamount:TextView
    private lateinit var Nmamount:TextView
    private lateinit var Nfamount:TextView
    private lateinit var Neduamount:TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayrequestedfund_details)
        initView()
        setValuesToViews()

       //updating
        btnUpdate=findViewById(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("fundid").toString(),
                intent.getStringExtra("accountname").toString()

            )
        }
        btnDelete=findViewById(R.id.btnDelete)
        //deleting
        btnDelete.setOnClickListener{
            deleterecord(
                intent.getStringExtra("fundid").toString()
            )
        }


    }
    //record delete
    private fun deleterecord(id:String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("funds").child(id)
        val mTask=dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"fund data deleted",Toast.LENGTH_LONG).show()
            val intent=Intent(this, request_history::class.java)
            finish()
            startActivity((intent))
        }.addOnFailureListener{err->
            Toast.makeText(this,"Deleting Err ${err.message}",Toast.LENGTH_LONG).show()

        }
    }
    private fun initView(){
        Naname=findViewById(R.id.Naname)
        Nano=findViewById(R.id.Nano)
        Nbname=findViewById(R.id.Nbname)
        Ncamount=findViewById(R.id.Ncamount)
        Nmamount=findViewById(R.id.Nmamount)
        Nfamount=findViewById(R.id.Nfamount)
        Neduamount=findViewById(R.id.Neduamount)


    }
    //fetching
    private fun setValuesToViews(){
        Naname.text=intent.getStringExtra("accountname")
        Nano.text=intent.getStringExtra("accountno")
        Nbname.text=intent.getStringExtra("bankname")
        Ncamount.text=intent.getStringExtra("clothesamount")
        Nmamount.text=intent.getStringExtra("medicalsamount")
        Nfamount.text=intent.getStringExtra("foodsamount")
        Neduamount.text=intent.getStringExtra("educationsamount")

    }

    //update dialog
    private fun openUpdateDialog(
        fundid:String,
        accountname:String

    ){
      val mdialog=AlertDialog.Builder(this)
        val inflater=layoutInflater
        val mdialogview=inflater.inflate(R.layout.activity_updatefundsdetails,null)

        mdialog.setView(mdialogview)
        val accountName=mdialogview.findViewById<EditText>(R.id.aname)
        val accountNo=mdialogview.findViewById<EditText>(R.id.ano)
        val  Bank=mdialogview.findViewById<EditText>(R.id.bankname)
        val Clothes=mdialogview.findViewById<EditText>(R.id.clothes)
        val medicals=mdialogview.findViewById<EditText>(R.id.medicals)
        val foods =mdialogview.findViewById<EditText>(R.id.foods)
        val educations=mdialogview.findViewById<EditText>(R.id.educations)
        val updatebtn=mdialogview.findViewById<Button>(R.id.update)

        accountName.setText(intent.getStringExtra("accountname").toString())
        accountNo.setText(intent.getStringExtra("accountno").toString())
        Bank.setText(intent.getStringExtra("bankname").toString())
        Clothes.setText(intent.getStringExtra("clothesamount").toString())
        medicals.setText(intent.getStringExtra("medicalsamount").toString())
        foods.setText(intent.getStringExtra("foodsamount").toString())
        educations.setText(intent.getStringExtra("educationsamount").toString())


        mdialog.setTitle("updating $accountname record")
        val alertDialog=mdialog.create()
        alertDialog.show()

        //update
        updatebtn.setOnClickListener{
            updateFundsdata(
                fundid,
                accountName.text.toString(),
                accountNo.text.toString(),
                Bank.text.toString(),
                Clothes.text.toString(),
                medicals.text.toString(),
                foods.text.toString(),
                educations.text.toString()

            )
            //setting updated data to the textview
            Naname.text=accountName.text.toString()
            Nano.text= accountNo.text.toString()
            Nbname.text=Bank.text.toString()
            Ncamount.text=Clothes.text.toString()
            Nmamount.text=medicals.text.toString()
            Nfamount.text=foods.text.toString()
            Neduamount.text=educations.text.toString()
            alertDialog.dismiss()
            Toast.makeText(applicationContext,"funds data updated",Toast.LENGTH_LONG).show()
        }



    }
    private fun updateFundsdata(
        id: String,
        accountname: String,
        Accountno: String,
        Bankname:String,
        clothesamount: String,
        medicalsamount: String,
        foodsamount: String,
        educationsamount: String,

    ){
        val dbRef=FirebaseDatabase.getInstance().getReference("funds").child(id)
        val fundinfo= fundsmodel(id,clothesamount,medicalsamount,foodsamount,educationsamount,accountname, Accountno,Bankname)
        dbRef.setValue(fundinfo)
    }
}