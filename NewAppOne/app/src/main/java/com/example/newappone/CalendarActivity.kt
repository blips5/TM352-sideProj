package com.example.newappone

import android.icu.util.Calendar.MONDAY
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CalendarActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)


        //Initialises all views
        val addNote = findViewById<FloatingActionButton>(R.id.addNote)
        val noteBox = findViewById<TextView>(R.id.noteBox)
        val editNote = findViewById<EditText>(R.id.editNote)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val noteAdd = findViewById<Button>(R.id.noteAdd)

        //Sets the calenderview to have Monday as the first day of the week.
        calendarView.firstDayOfWeek = MONDAY

        //Makes the editable note box and noteAdd button invisible from the start until
        //the addNote button is pressed
        editNote.visibility = View.INVISIBLE
        noteAdd.visibility = View.INVISIBLE






        //Opens the editable note box when the addNote button is pressed
        addNote.setOnClickListener{
            if(editNote.visibility == View.INVISIBLE)
            {
                editNote.visibility = View.VISIBLE
                noteAdd.visibility = View.VISIBLE
                noteBox.visibility = View.INVISIBLE
            }
            else
            {
                editNote.visibility = View.INVISIBLE
                noteAdd.visibility = View.INVISIBLE
                noteBox.visibility = View.VISIBLE
            }
        }

        noteAdd.setOnClickListener{
            noteBox.append("\n" + editNote.text)
            editNote.text.clear()
            editNote.visibility = View.INVISIBLE
            noteAdd.visibility = View.INVISIBLE
            noteBox.visibility = View.VISIBLE
        }

    }
}
