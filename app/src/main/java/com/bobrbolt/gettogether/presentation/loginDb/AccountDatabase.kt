package com.bobrbolt.gettogether.presentation.loginDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountDao() : AccountDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase?= null

        fun getDatabase(context: Context): AccountDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AccountDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AccountDatabase::class.java,
                "account_database"
            ).build()
        }
    }
}