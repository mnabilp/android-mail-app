package com.android.mailapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "mail_list")
@Parcelize
data class Mail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var emailAddress: String,
    var emailContent: String,
    val emailDate: Long
): Parcelable