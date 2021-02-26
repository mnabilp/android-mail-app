package com.android.mailapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.android.mailapp.R
import com.android.mailapp.model.Mail
import kotlinx.android.synthetic.main.activity_compose_mail.*

class ComposeMailActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE_ADD = 1000
        const val INTENT_NOTE = "intent_note"

        fun launchAddNotePage(activity: Activity){
            val intent = Intent(activity, ComposeMailActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_mail)

        listener()

        val backToMainActivity = findViewById<View>(R.id.btn_add_note_back)
        backToMainActivity.setOnClickListener {
            onBackPressed()
        }
    }

    private fun listener() {
        btn_send_compose_mail.setOnClickListener {
            val data = Intent()
            val emailAddress = et_email_compose_mail.text.toString().trim()
            val mailContent = et_content_compose_mail.text.toString().trim()
            val createdAt = System.currentTimeMillis()
            if (TextUtils.isEmpty(emailAddress)){
                setResult(Activity.RESULT_CANCELED, data)
            } else {
                val note = Mail(emailAddress = emailAddress, emailContent = mailContent, emailDate = createdAt)
                data.putExtra(INTENT_NOTE, note)
                setResult(Activity.RESULT_OK, data)
            }

            finish()
        }
    }
}