
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
import io.reactivex.Observable


class CalendarActivity : AppCompatActivity() {


    private var db: AppDatabase? = null
    private var notesDao: NotesDao? = null


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


            Observable.fromCallable({
                db = AppDatabase.getAppDataBase(context = this)
                notesDao = db?.notesDao()

                var notes = Notes(name = editNote.text.toString())

                with(notesDao){
                    this?.insertNote(notes)
                }
                db?.notesDao()?.getNotes()
            }).doOnNext({ list ->
                var finalString = ""
                list?.map { finalString+= it.name+" - " }
                noteBox.text = finalString
            })



            editNote.text.clear()
            editNote.visibility = View.INVISIBLE
            noteAdd.visibility = View.INVISIBLE
            noteBox.visibility = View.VISIBLE
        }

    }
}
