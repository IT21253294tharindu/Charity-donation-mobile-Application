package com.example.funds0.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R
import com.example.funds0.models.CharityModel
import com.example.funds0.activities.SignupSelection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class Charity_RegisterationForm : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var btnNextRegistration: Button
    private lateinit var etCharityName: EditText
    private lateinit var etCharityOgName: EditText
    private lateinit var etCharityVold: EditText
    private lateinit var etCharityEmail: EditText
    private lateinit var etCharityAddress: EditText
    private lateinit var etCharityTelNo: EditText
    private lateinit var etCharityPersonalNo: EditText
    private lateinit var etCharityWeb: EditText
    private lateinit var etCharityPass: EditText

    private lateinit var dbRef: DatabaseReference
    var user=Firebase.auth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity_registeration_form)

        mAuth = Firebase.auth


        //form details
        etCharityName = findViewById(R.id.formCharityName)
        etCharityOgName = findViewById(R.id.formOgName)
        etCharityVold = findViewById(R.id.formRegistrationID)
        etCharityEmail = findViewById(R.id.formCharityEmail)
        etCharityAddress = findViewById(R.id.formCharityAddress)
        etCharityTelNo = findViewById(R.id.formTeleNo)
        etCharityPersonalNo = findViewById(R.id.formPersonNum)
        etCharityWeb = findViewById(R.id.formcharityWebsite)
        etCharityPass = findViewById(R.id.formCharityPassword)

        //db connection
        dbRef = FirebaseDatabase.getInstance().getReference("CharityInfo")


        //Next button assigning
        btnNextRegistration = findViewById<Button>(R.id.btnRegisternext)

        //profile picture page linking
        btnNextRegistration = findViewById<Button>(R.id.btnRegisternext)
        btnNextRegistration.setOnClickListener {

            //registration data insertion
            saveCharityDetails()
        }

        //backButton
        val BackBtn = findViewById<Button>(R.id.btnBackCharityRes)
        BackBtn.setOnClickListener {
            val intent = Intent(this, SignupSelection::class.java)
            startActivity(intent)
        }



    }


    //save charity details
    private fun saveCharityDetails() {

        lateinit var cId: String
        //get values
        var charityName = etCharityName.text.toString()
        var charityOgName = etCharityOgName.text.toString()
        var CharityVold = etCharityVold.text.toString()
        var CharityEmail = etCharityEmail.text.toString()
        var CharityAddress = etCharityAddress.text.toString()
        var CharityTelNo = etCharityTelNo.text.toString()
        var CharityPersonalNo = etCharityPersonalNo.text.toString()
        var CharityWeb = etCharityWeb.text.toString()
        var CharityPass = etCharityPass.text.toString()


        if (charityName.isEmpty() || charityOgName.isEmpty() || CharityTelNo.isEmpty() || CharityVold.isEmpty() || CharityPass.isEmpty() || CharityAddress.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
        } else if (CharityPass.length < 6) {
            Toast.makeText(
                this,
                "Please make sure your password is more than six characters long",
                Toast.LENGTH_LONG
            ).show()
        } else {

            mAuth.createUserWithEmailAndPassword(CharityEmail, CharityPass)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "user created", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "createUserWithEmail:success")
                        user = mAuth.currentUser

                        if (user !== null) {
                            cId = user!!.uid
                        }

                        print(cId)
                    }

                    if (it.isCanceled){
                        Toast.makeText(this, "user creation failed", Toast.LENGTH_SHORT).show()
                    }

                    val charity = CharityModel(
                        cId,
                        charityName,
                        charityOgName,
                        CharityVold,
                        CharityEmail,
                        CharityAddress,
                        CharityTelNo,
                        CharityPersonalNo,
                        CharityWeb,
                        CharityPass
                    )

                    dbRef.child(cId).setValue(charity).addOnCompleteListener {
                        Toast.makeText(this, " Registration Successful ", Toast.LENGTH_LONG)
                            .show()
                        val profileintn=Intent(this, Charity_Profile::class.java)

                    }.addOnFailureListener { err ->
                        Toast.makeText(this, " Error ${err.message} ", Toast.LENGTH_LONG).show()
                    }
                }


        }

    }
}