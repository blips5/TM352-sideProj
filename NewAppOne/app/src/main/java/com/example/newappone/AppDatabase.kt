package com.example.newappone

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Notes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if(INSTANCE == null)
            {
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }


        fun destroyDataBase()
        {
            INSTANCE = null
        }
    }
}