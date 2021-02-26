package com.android.mailapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mailapp.model.Mail

@Dao
interface MailDao {

    @Query("SELECT * FROM mail_list ORDER BY emailDate DESC")
    fun getEmailList(): LiveData<List<Mail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(email: Mail)

    @Query("DELETE FROM mail_list WHERE id =:id")
    suspend fun deleteMail(id: Int)

    @Update
    suspend fun edit(email: Mail)

}