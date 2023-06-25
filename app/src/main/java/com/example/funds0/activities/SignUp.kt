package com.example.funds0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.funds0.models.UserModel
import com.example.funds0.LogIn
import com.example.funds0.profilecreation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var tele: EditText
    private lateinit var usern: EditText
    private lateinit var password: EditText
    private lateinit var rePassw: EditText
    private lateinit var terms: CheckBox
//    private val databaseRef = DatabaseUtil.getDatabase("User")

    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById<EditText>(R.id.nameInput)
        email = findViewById<EditText>(R.id.emailInput)
        tele = findViewById<EditText>(R.id.telInput)
        usern = findViewById<EditText>(R.id.usernInput2)
        password = findViewById<EditText>(R.id.passwInput2)
        rePassw = findViewById<EditText>(R.id.rePasswInput)
        terms = findViewById(R.id.termsNCond)

        dbRef = FirebaseDatabase.getInstance().getReference("User")
        auth = Firebase.auth

        val backbtn = findViewById<Button>(R.id.backSignUp)
        backbtn.setOnClickListener {
            val mainint = Intent(this, LogIn::class.java)
            startActivity(mainint)
        }

        val signup = findViewById<Button>(R.id.signUpBtn2)
        signup.setOnClickListener {
            saveUserDeet()
        }

    }

    private fun saveUserDeet() {
        lateinit var userID:String
        val names = name.text.toString()
        val emails = email.text.toString()
        val fone = tele.text.toString()
        val username = usern.text.toString()
        val passwords = password.text.toString()
        val rePasswords = rePassw.text.toString()

        if (names.isEmpty() || emails.isEmpty() || fone.isEmpty() || username.isEmpty() || passwords.isEmpty() || rePasswords.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
        } else if (passwords.length < 6) {
            Toast.makeText(
                this,
                "Please make sure your password is more than six characters long",
                Toast.LENGTH_LONG
            ).show()
        } else if (!passwords.equals(rePasswords)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                        val user=auth.currentUser
                        if (user != null) {
                            userID=user.uid
                        }
                    }

                    val userObj = UserModel(userID, names, emails, fone, username, passwords)

                    dbRef.child(userID).setValue(userObj)
                        .addOnCompleteListener {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_LONG).show()
                            val profileint = Intent(this, profilecreation::class.java)
                            startActivity(profileint)
                        }.addOnFailureListener { err ->
                            Toast.makeText(this, "Sign Up Unsuccessful ${err.message}", Toast.LENGTH_LONG)
                                .show()
                        }
                }


        }
    }
}