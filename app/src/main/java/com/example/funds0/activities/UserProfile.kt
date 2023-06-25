package com.example.funds0

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.funds0.activities.donorHomepage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserProfile : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var namedisp: TextView
    private lateinit var usernamedisp: TextView
    private lateinit var emaildisp: TextView
    private lateinit var telephonedisp: TextView
    private lateinit var passworddisp: TextView
    private lateinit var descriptiondisp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        dbRef = Firebase.database.reference
        var user = Firebase.auth.currentUser

        if (user == null) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
        }

        dbRef.child("User").child(user!!.uid).child("names").get()
            .addOnSuccessListener { dataSnapshot ->
                var name = dataSnapshot.value.toString()
                namedisp = findViewById<TextView>(R.id.nameDisp)
                namedisp.text = name
            }
            .addOnFailureListener {
                println("failed to retrieve name")
            }

        dbRef.child("User").child(user!!.uid).child("username").get()
            .addOnSuccessListener { dataSnapshot ->
                var username = dataSnapshot.value.toString()
                usernamedisp = findViewById<TextView>(R.id.usernDisplay)
                usernamedisp.text = username
            }
            .addOnFailureListener {
                println("failed to retrieve username")
            }

        dbRef.child("User").child(user!!.uid).child("emails").get()
            .addOnSuccessListener { dataSnapshot ->
                var email = dataSnapshot.value.toString()
                emaildisp = findViewById<TextView>(R.id.emailDisp)
                emaildisp.text = email
            }
            .addOnFailureListener {
                println("failed to retrieve email")
            }

        dbRef.child("User").child(user!!.uid).child("fone").get()
            .addOnSuccessListener { dataSnapshot ->
                var phone = dataSnapshot.value.toString()
                telephonedisp = findViewById<TextView>(R.id.telDisp)
                telephonedisp.text = phone
            }
            .addOnFailureListener {
                println("failed to retrieve phone number")
            }

        dbRef.child("User").child(user!!.uid).child("passwords").get()
            .addOnSuccessListener { dataSnapshot ->
                var password = dataSnapshot.value.toString()
                passworddisp = findViewById<TextView>(R.id.passwDisp)
                passworddisp.text = password
            }
            .addOnFailureListener {
                println("failed to retrieve password")
            }

        dbRef.child("User").child(user!!.uid).child("description").get()
            .addOnSuccessListener { dataSnapshot ->
                var desc = dataSnapshot.value.toString()
                descriptiondisp = findViewById<TextView>(R.id.discDip)
                descriptiondisp.text = desc
            }
            .addOnFailureListener {
                println("failed to retrieve password")
            }


        val edit = findViewById<Button>(R.id.edtBtn)
        edit.setOnClickListener {
            val editInt = Intent(this, EditProfile::class.java)
            startActivity(editInt)
        }

        val delete = findViewById<Button>(R.id.deleteBtn)
        delete.setOnClickListener {
            deleteUser()
            val homeint = Intent(this, donorHomepage::class.java)
            startActivity(homeint)
        }

        val back = findViewById<Button>(R.id.backProfile)
        back.setOnClickListener {
            val homeint = Intent(this, donorHomepage::class.java)
            startActivity(homeint)
        }
    }

    private fun deleteUser() {
        var user = Firebase.auth.currentUser
        if (user != null) {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dbRef.child("User").child(user!!.uid).removeValue()
                        Log.d(TAG, "User account deleted.")
                        Toast.makeText(this,"Your account has been deleted...", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


}