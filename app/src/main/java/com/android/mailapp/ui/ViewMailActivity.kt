package com.android.mailapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.mailapp.R
import com.android.mailapp.model.Mail
import com.android.mailapp.viewmodel.MailViewModel
import kotlinx.android.synthetic.main.activity_view_mail.*

class ViewMailActivity : AppCompatActivity() {

    private var notes: Mail? = null
    private lateinit var notesViewModel: MailViewModel

    companion object{

        const val INTENT_NOTE_ADD = "intent_note_add"

        fun launchDetailNote(activity: AppCompatActivity, notes: Mail){
            val intent = Intent(activity, ViewMailActivity::class.java)
            intent.putExtra(INTENT_NOTE_ADD, notes)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_mail)
        notesViewModel = ViewModelProvider(this).get(MailViewModel::class.java)

        handleIntent()
        initUI()
        listener()

        val backToMainActivity = findViewById<View>(R.id.btn_detail_note_back)
        backToMainActivity.setOnClickListener {
            startActivity(Intent(this@ViewMailActivity, HomeActivity::class.java))
        }
    }

    private fun handleIntent() {
        notes = intent.getParcelableExtra(INTENT_NOTE_ADD)
    }

    private fun initUI() {
        updateUI(notes)
    }

    private fun updateUI(notes: Mail?) {
        tv_mail_title.text = notes?.emailAddress
        tv_mail_content.text = notes?.emailContent
    }

    private fun listener() {
        buttonDelete.setOnClickListener {
            notesViewModel.deleteMail(notes?.id ?: 0)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ComposeMailActivity.REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<Mail>(ComposeMailActivity.INTENT_NOTE)?.let { notes ->
                this.notes?.let { oldNote ->
                    oldNote.emailAddress = notes.emailAddress
                    oldNote.emailContent = notes.emailContent
                    notesViewModel.editMail(oldNote)
                    updateUI(oldNote)
                }
            }
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }

}