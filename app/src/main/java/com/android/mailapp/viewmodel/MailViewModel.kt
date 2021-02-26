package com.android.mailapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.mailapp.database.MailDatabase
import com.android.mailapp.model.Mail
import com.android.mailapp.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MailViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MailRepository
    val listInbox: LiveData<List<Mail>>

    init {
       val mailDao = MailDatabase.getDatabase(application).mailDao()
        repository = MailRepository(mailDao)
        listInbox = repository.getInboxList
    }

    fun composeMail(mail: Mail) = viewModelScope.launch(Dispatchers.IO){
        repository.composeMail(mail)
    }

    fun editMail(mail: Mail) = viewModelScope.launch(Dispatchers.IO){
        repository.editMail(mail)
    }

    fun deleteMail(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMail(id)
    }

}