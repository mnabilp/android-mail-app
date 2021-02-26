package com.android.mailapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.mailapp.R

class DraftActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draft)

        val backToMainActivity = findViewById<View>(R.id.btn_fav_back)
        backToMainActivity.setOnClickListener {
            startActivity(Intent(this@DraftActivity, HomeActivity::class.java))
        }

    }

}