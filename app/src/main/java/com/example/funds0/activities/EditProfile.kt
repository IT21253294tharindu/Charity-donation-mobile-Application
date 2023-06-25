package com.example.funds0

import android.content.ClipDescription
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class EditProfile : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password:EditText
    private lateinit var telephone:EditText
    private lateinit var description: EditText
    val user=Firebase.auth.currentUser
    var dbRef=FirebaseDatabase.getInstance().getReference("User")
    lateinit var toProf:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        toProf= Intent(this, UserProfile::class.java )
        name=findViewById(R.id.editTextTextPersonName2)
        username=findViewById(R.id.usernEdit)
        email=findViewById(R.id.emailEdit)
        password=findViewById(R.id.passwEdit)
        telephone=findViewById(R.id.telEdit)
        description=findViewById(R.id.descEdit)

        val back=findViewById<Button>(R.id.backBtn3)
        back.setOnClickListener {
            startActivity(toProf)
        }

        val save=findViewById<Button>(R.id.saveBtn3)
        save.setOnClickListener {
            saveEdits()

        }
    }

    private fun saveEdits() {
        val passw=password.text.toString()
        val email=email.text.toString()

        val names=name.text.toString()
        val usern=username.text.toString()
        val tel=telephone.text.toString()
        val desc=description.text.toString()

        if (email.isNotBlank()){
            user!!.updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dbRef.child(user!!.uid).child("emails").setValue(email)
                        Log.d(TAG, "User email address updated.")
                    }
                }
        }

        if (passw.isNotBlank()){
            user!!.updatePassword(passw)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dbRef.child(user!!.uid).child("passwords").setValue(passw)
                        Log.d(TAG, "User password updated.")
                    }
                }
        }

        if ( names.isNotBlank() ){
            dbRef.child(user!!.uid).child("names").setValue(names)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "name updated.")
                    }
                }
        }
        if (tel.isNotBlank()){
            dbRef.child(user!!.uid).child("fone").setValue(tel)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "telno updated.")
                    }
                }
        }
        if (desc.isNotBlank()){
            dbRef.child(user!!.uid).child("description").setValue(desc)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "description updated.")
                    }
                }
        }
        if (usern.isNotBlank()){
            dbRef.child(user!!.uid).child("username").setValue(usern)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "username updated.")
                    }
                }

        }

        startActivity(toProf)

    }
}