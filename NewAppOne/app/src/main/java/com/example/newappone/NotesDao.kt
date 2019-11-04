package com.example.newappone

import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)

    @Query("SELECT * FROM Notes")
    fun getNotes(): List<Notes>




}