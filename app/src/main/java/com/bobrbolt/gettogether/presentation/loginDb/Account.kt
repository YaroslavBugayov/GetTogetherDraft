package com.bobrbolt.gettogether.presentation.loginDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = false)
    var login: String,

    @ColumnInfo(name = "token")
    val token: String
)