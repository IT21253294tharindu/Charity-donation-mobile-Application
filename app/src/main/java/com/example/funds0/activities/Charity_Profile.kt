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
import com.example.funds0.adapters.CharityAdapter
import com.example.funds0.models.CharityModel
import com.example.funds0.activities.Update_Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Charity_Profile : AppCompatActivity() {

    private lateinit var tvCharityVoId : TextView
    private lateinit var tvCharityName : TextView
    private lateinit var tvCharityOgName : TextView
    private lateinit var tvCharityEmail : TextView
    private lateinit var tvCharityAddress : TextView
    private lateinit var tvCharityTelNo : TextView
    private lateinit var tvCharityPersonNo : TextView
    private lateinit var tvCharityWeb : TextView
    private lateinit var tvCharityPass : TextView

    private lateinit var dbRef: DatabaseReference
    val user=Firebase.auth.currentUser

    //buttons
    private lateinit var backBtn : Button
    private lateinit var updateBtn : Button
    private lateinit var deleteBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity_profile)

        //authentication
        dbRef = Firebase.database.reference


        if (user == null) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
        }else{


            dbRef.child("CharityInfo").child(user!!.uid).child("charityName").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityName = dataSnapshot.value.toString()
                    tvCharityName = findViewById<TextView>(R.id.CharityName)
                    tvCharityName.text = charityName
                } .addOnFailureListener {
                    println("failed to retrieve charity name")
                }


            dbRef.child("CharityInfo").child(user!!.uid).child("charityVold").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityVoid = dataSnapshot.value.toString()
                    tvCharityVoId = findViewById<TextView>(R.id.RegistrationID)
                    tvCharityVoId.text = charityVoid
                }.addOnFailureListener {
                    println("failed to retrieve void")
                }

            dbRef.child("CharityInfo").child(user!!.uid).child("charityOgName").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charitOgname = dataSnapshot.value.toString()
                    tvCharityOgName = findViewById<TextView>(R.id.OgName)
                    tvCharityOgName.text = charitOgname
                }.addOnFailureListener {
                    println("failed to retrieve oganizer")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityEmail").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityEmail = dataSnapshot.value.toString()
                    tvCharityEmail = findViewById<TextView>(R.id.CharityEmail)
                    tvCharityEmail.text = charityEmail
                }.addOnFailureListener {
                    println("failed to retrieve email")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityAddress").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityAddress = dataSnapshot.value.toString()
                    tvCharityAddress = findViewById<TextView>(R.id.CharityAddress)
                    tvCharityAddress.text = charityAddress
                }.addOnFailureListener {
                    println("failed to retrieve address")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityTelNo").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityTelNo = dataSnapshot.value.toString()
                    tvCharityTelNo = findViewById<TextView>(R.id.TeleNo)
                    tvCharityTelNo.text = charityTelNo
                }.addOnFailureListener {
                    println("failed to retrieve Tele No")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityPersonalNo").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityPersonNo = dataSnapshot.value.toString()
                    tvCharityPersonNo = findViewById<TextView>(R.id.PersonNum)
                    tvCharityPersonNo.text = charityPersonNo
                }.addOnFailureListener {
                    println("failed to retrieve personalNumber")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityWeb").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityWeb = dataSnapshot.value.toString()
                    tvCharityWeb = findViewById<TextView>(R.id.charityWebsite)
                    tvCharityWeb.text = charityWeb
                }.addOnFailureListener {
                    println("failed to retrieve web")
                }
            dbRef.child("CharityInfo").child(user!!.uid).child("charityPass").get()
                .addOnSuccessListener { dataSnapshot ->
                    var charityPassword = dataSnapshot.value.toString()
                    tvCharityPass = findViewById<TextView>(R.id.charityPassword)
                    tvCharityPass.text = charityPassword
                }.addOnFailureListener {
                    println("failed to retrieve password")
                }
        }





        //link back
        backBtn = findViewById(R.id.btnBackCharityprofile)
        backBtn.setOnClickListener{
            val intent = Intent(this, AllCharities::class.java)
            startActivity(intent)
        }

        //update data
        updateBtn = findViewById(R.id.btnCharityProfileEdt)

        updateBtn.setOnClickListener {
            val intent = Intent(this@Charity_Profile, Update_Profile::class.java)

            startActivity(intent)
        }

        //delete data
        deleteBtn = findViewById(R.id.btnCharityProfileDelete)
        deleteBtn.setOnClickListener{
            deleteProfile()
        }


    }

    //deleteProfile Function
    private fun deleteProfile(){

        val dbRef = FirebaseDatabase.getInstance().getReference("CharityInfo").child(user!!.uid)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Your charity profile deleted Successfully",Toast.LENGTH_LONG).show()

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this,"Deleting error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

}