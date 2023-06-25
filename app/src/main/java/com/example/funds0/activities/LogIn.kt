package com.example.funds0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.funds0.activities.donorHomepage
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var emailinput:EditText
    private lateinit var passwinput: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailinput=findViewById<EditText>(R.id.usernInput)
        passwinput=findViewById<EditText>(R.id.passwInput)

        auth= FirebaseAuth.getInstance()

        val sign= findViewById<Button>(R.id.signUpBtn)
        sign.setOnClickListener {
            val signint=Intent(this, SignUp::class.java)
            startActivity(signint)
        }

        val login=findViewById<Button>(R.id.logInBtn)
        login.setOnClickListener {
            userLogin()
        }

        val cancellog= findViewById<Button>(R.id.cancelBtn)
        cancellog.setOnClickListener {
            emailinput.text.clear()
            passwinput.text.clear()
        }
    }

    private fun userLogin() {
        val emails=emailinput.text.toString()
        val passwords=passwinput.text.toString()

        if(emails.isEmpty() || passwords.isEmpty()){
            Toast.makeText(this, "Please Enter Your LogIn Details...", Toast.LENGTH_SHORT).show()
        }


        auth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Log in Successful..", Toast.LENGTH_SHORT).show()
                val home= Intent(this, donorHomepage::class.java)
                startActivity(home)
            }else{
                emailinput.text.clear()
                passwinput.text.clear()
                Toast.makeText(this, "Log in Unsuccessful... Try Again", Toast.LENGTH_LONG).show()
            }
        }

    }
}