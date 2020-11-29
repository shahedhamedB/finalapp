package com.example.tkafl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.tkafl.ui.varify.VerifyActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    var codeSent: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth=FirebaseAuth.getInstance()
        var currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
        loginbtn.setOnClickListener {
            login()
        }


    }

    private fun login() {
        Toast.makeText(applicationContext, "this ", Toast.LENGTH_SHORT).show()
        val mobileNumber=findViewById<EditText>(R.id.phoneNumber)


        val phoneNumber = mobileNumber!!.text.toString()
        if (phoneNumber.isEmpty()) {
            mobileNumber!!.error = "mobile number cannot be empty"
            mobileNumber!!.requestFocus()
        }
        if (phoneNumber.length < 9) {
            mobileNumber!!.error = "Please enter a valid phone"
            mobileNumber!!.requestFocus()
        }else{
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+962$phoneNumber",  // Phone number to verify (I hardcoded it only for Indian Mobile numbers).
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                mCallbacks
            )
        }


    }

    var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
            override fun onVerificationFailed(e: FirebaseException) {}
            override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                codeSent = s
                var intent = Intent(applicationContext, VerifyActivity::class.java)
                intent.putExtra("storedVerificationId",codeSent)
                startActivity(intent)
                finish()
            }
        }


}