package com.bobrbolt.gettogether.loginDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Update
    fun update(account: Account)

    @Query("SELECT * FROM accounts")
    fun getAllAccounts() : List<Account>

    @Query("DELETE FROM accounts")
    fun clearTable()

    @Query("SELECT count(*) FROM accounts")
    fun getCount(): Int
}