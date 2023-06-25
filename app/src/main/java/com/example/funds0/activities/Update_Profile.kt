package com.example.funds0.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.funds0.R
import com.example.funds0.models.CharityModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Update_Profile : AppCompatActivity() {

    private lateinit var tvCharityVoId: EditText
    private lateinit var tvCharityName: EditText
    private lateinit var tvCharityOgName: EditText
    private lateinit var tvCharityEmail: EditText
    private lateinit var tvCharityAddress: EditText
    private lateinit var tvCharityTelNo: EditText
    private lateinit var tvCharityPersonNo: EditText
    private lateinit var tvCharityWeb: EditText
    private lateinit var tvCharityPass: EditText

    val user = Firebase.auth.currentUser
    var dbRef = FirebaseDatabase.getInstance().getReference("CharityInfo")
    lateinit var SaveP: Intent

    private lateinit var updateBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)


        SaveP = Intent(this, Charity_Profile::class.java)
        tvCharityVoId = findViewById(R.id.etCharityVoid)
        tvCharityName = findViewById(R.id.etCharityName)
        tvCharityOgName = findViewById(R.id.etCharityOgName)
        tvCharityWeb = findViewById(R.id.etCharityWeb)
        tvCharityEmail = findViewById(R.id.etCharityEmail)
        tvCharityAddress = findViewById(R.id.etCharityAddress)
        tvCharityTelNo = findViewById(R.id.etCharityTel)
        tvCharityPersonNo = findViewById(R.id.etCharityPersonNo)
        tvCharityPass = findViewById(R.id.etCharityPass)

        updateBtn = findViewById(R.id.btnUpdateData)

        val save = findViewById<Button>(R.id.btnUpdateData)
        save.setOnClickListener {
            saveEdits()

        }
    }


    private fun saveEdits() {
        val Cmail = tvCharityEmail.text.toString()
        val Cpass = tvCharityPass.text.toString()

        val Cname = tvCharityName.text.toString()
        val COgname = tvCharityOgName.text.toString()
        val Cweb = tvCharityWeb.text.toString()
        val Caddress = tvCharityAddress.text.toString()
        val CtelNo = tvCharityTelNo.text.toString()
        val CpNo = tvCharityPersonNo.text.toString()
        val CvoID = tvCharityVoId.text.toString()

        if (Cmail.isNotBlank()) {
            user!!.updateEmail(Cmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dbRef.child(user!!.uid).child("CharityEmail").setValue(Cmail)
                        Log.d(ContentValues.TAG, "User email address updated.")
                    }
                }
        }

        if (Cpass.isNotBlank()) {
            user!!.updatePassword(Cpass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dbRef.child(user!!.uid).child("charityPass").setValue(Cpass)
                        Log.d(ContentValues.TAG, "User password updated.")
                    }
                }
        }

        if (Cname.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityName").setValue(Cname)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Charity name updated.")
                    }
                }
        }

        if (COgname.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityOgName").setValue(COgname)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Organizer name updated.")
                    }
                }
        }
        if (Cweb.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityWeb").setValue(Cweb)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Website updated.")
                    }
                }
        }
        if (Caddress.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityAddress").setValue(Caddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Address updated.")
                    }
                }
        }
        if (CtelNo.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityTelNo").setValue(CtelNo)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Address updated.")
                    }
                }
        }
        if (CpNo.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityPersonalNo").setValue(CpNo)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Personal Number updated.")
                    }
                }
        }
        if (CvoID.isNotBlank()) {
            dbRef.child(user!!.uid).child("charityVold" ).setValue(CvoID)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Registration ID updated.")
                    }
                }
        }


        startActivity(SaveP)
    }
}