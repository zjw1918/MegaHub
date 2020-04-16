package io.mega.megahub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getIns(context: Context): AppDatabase {
            val tempIns = INSTANCE
            if (tempIns != null) return tempIns

            synchronized(this) {
                val ins = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                ).build()
                INSTANCE = ins
                return ins
            }
        }
    }
}