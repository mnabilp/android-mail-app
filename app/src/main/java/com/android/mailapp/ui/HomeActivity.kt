package com.android.mailapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mailapp.NavDrawerActivity
import com.android.mailapp.R
import com.android.mailapp.adapter.ComposeMailAdapter
import com.android.mailapp.model.Mail
import com.android.mailapp.viewmodel.MailViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private var mailAdapter: ComposeMailAdapter? = null
    private lateinit var mailViewModel: MailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mailViewModel = ViewModelProvider(this).get(MailViewModel::class.java)

        initiateUI()
        listener()
        observer()

        val goToDraft = findViewById<View>(R.id.btn_navbar)
        goToDraft.setOnClickListener {
            startActivity(Intent(this@HomeActivity, NavDrawerActivity::class.java))
        }

    }

    private fun observer() {
        mailViewModel.listInbox.observe(this, Observer { mail ->
            if (mail != null){
                mailAdapter?.composeMail(mail)
            }
        })
    }

    private fun listener() {
        fabAddNote.setOnClickListener {
            ComposeMailActivity.launchAddNotePage(this@HomeActivity)
        }
    }

    private fun initiateUI(){
        if (mailAdapter == null){
            mailAdapter = ComposeMailAdapter(this, seeMoreDetails = { notes ->
                ViewMailActivity.launchDetailNote(this, notes)
            })
            with(rv_mail_list_inbox){
                layoutManager = LinearLayoutManager(this@HomeActivity)
                addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
                setHasFixedSize(true)
                adapter = mailAdapter
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ComposeMailActivity.REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<Mail>(ComposeMailActivity.INTENT_NOTE)?.let { notes ->
                mailViewModel.composeMail(notes)
            }
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }

}