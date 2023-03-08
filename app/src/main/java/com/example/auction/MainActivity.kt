package com.example.auction

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.auction.activity.LoginActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }
   fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun exit(view: View) {
        finish()
    }


}