package com.example.loginandsignup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignup.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser

import kotlin.getValue

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()

        val text = "Welcome Back!"
        val spannableText = SpannableString(text)
        spannableText.setSpan(ForegroundColorSpan(Color.parseColor("#312222")), 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableText.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")), 7, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.welcome.text = spannableText

        binding.signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val passwd = binding.password.text.toString()
            if (email.isEmpty() || passwd.isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(email, passwd)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this,"Login failed ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

}