package com.example.krutik_exam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity()  {
    private lateinit var preferences: SharedPreferences

    private lateinit var inputPassword: EditText
    private lateinit var enterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputPassword = findViewById(R.id.auth_password_input)
        enterButton = findViewById(R.id.auth_enter_button)

        enterButton.setOnClickListener(onClickListener)

        preferences = getSharedPreferences("exam", Context.MODE_PRIVATE)
        if (preferences.getBoolean("IS_LOGINED", false)){
            val intent = Intent(this, ComicsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.auth_enter_button -> {
                val password = inputPassword.text.toString()

                if (password == ""){
                    Toast.makeText(this, "Введите пароль", Toast.LENGTH_LONG).show()
                } else {
                    if (password == "admin"){
                        preferences = getSharedPreferences("exam", Context.MODE_PRIVATE)
                        preferences.edit().putBoolean("IS_LOGINED", true).commit()
                        val intent = Intent(this, ComicsActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}