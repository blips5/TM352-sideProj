package com.example.newappone


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)