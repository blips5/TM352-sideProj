
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


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

        noteAdd.setOnClickListener {
            // Collects input data
            var noteText = editNote.text.toString()

            //noteBox.append("\n" + noteText)
            editNote.visibility = View.INVISIBLE
            noteAdd.visibility = View.INVISIBLE
            noteBox.visibility = View.VISIBLE


            Observable.fromCallable{
                var notes = Notes(name = noteText)

                with(notesDao) {
                    this?.insertNote(notes)
                }
                db?.notesDao()?.getNotes()
            }.doOnNext{
                // prints full list of notes in the database table Notes
                // needs changing to get a single note by date
                    list -> var finalString = " "
                list?.map { finalString+= it.name+ " - "}
                // Adds string to note box
                noteBox.text = finalString

            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            editNote.text.clear()
        }

    }
}
