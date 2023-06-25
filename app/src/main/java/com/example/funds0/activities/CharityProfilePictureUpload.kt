package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funds0.R

class CharityProfilePictureUpload : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity_profile_picture_upload)

        //Back button
        val BackBtn = findViewById<Button>(R.id.btnBackCharityprofile2)
        BackBtn.setOnClickListener {
            val intent = Intent(this, Charity_RegisterationForm::class.java)
            startActivity(intent)
        }
    }
}