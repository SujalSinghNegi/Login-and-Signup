package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignup.databinding.ActivitySignUpBinding
import com.google.firebase.ktx.Firebase
import kotlin.getValue
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth= FirebaseAuth.getInstance()


        binding.signupbtn2.setOnClickListener{
            val userName = binding.newUser.text.toString()
            val email = binding.newEmail.text.toString()
            val passwd = binding.newPass.text.toString()
            val repeat_passwd = binding.repeatPasswd.text.toString()
            if(userName.isEmpty() || email.isEmpty() || passwd.isEmpty() || repeat_passwd.isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                Log.d("TAG", "onCreate: ${userName} ${email} ${passwd} ${repeat_passwd}")
            }
            else if(passwd != repeat_passwd){
             Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email, passwd)
                    .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Sign up successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Sign up failed ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                    }

                    }

            }
        }

    }
}