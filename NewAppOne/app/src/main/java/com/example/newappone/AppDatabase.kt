package com.example.newappone

import android.content.Context
import androidx.room.*



@Database(entities = [Notes::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao


    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if(INSTANCE == null)
            {
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }


        fun destroyDatabase()
        {
            INSTANCE = null
        }
    }
}