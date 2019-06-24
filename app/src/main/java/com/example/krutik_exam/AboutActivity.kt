package com.example.krutik_exam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_comics.*

class AboutActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        exitButton = findViewById(R.id.exit_button)

        exitButton.setOnClickListener(onClickListener)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_about
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.exit_button -> {
                preferences = getSharedPreferences("exam", Context.MODE_PRIVATE)
                preferences.edit().putBoolean("IS_LOGINED", false).commit()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_comics -> {
                val intent = Intent(this, ComicsActivity::class.java)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_creators -> {
                //open intent
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourite -> {
                //open intent
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                //do nothing
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}