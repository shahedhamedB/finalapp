package com.example.tkafl.ui.varify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tkafl.HomeActivity
import com.example.tkafl.R
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_verify.*


class VerifyActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var TAG="varify"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)
        auth=FirebaseAuth.getInstance()
        val codeSent=intent.getStringExtra("storedVerificationId")
        verifyBtn.setOnClickListener{

            val code = id_otp!!.text.toString()
            val credential = PhoneAuthProvider.getCredential(codeSent!!, code)
            signInWithPhoneAuthCredential(credential)

        }



    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("verifyCode", "signInWithCredential:success")
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                    //FirebaseUser user = task.getResult().getUser();
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("verifyCode", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            this,
                            "" + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

}