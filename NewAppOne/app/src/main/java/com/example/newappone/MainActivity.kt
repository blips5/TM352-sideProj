package com.example.newappone


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcButton = findViewById<Button>(R.id.calcButton)
        val mapButton = findViewById<Button>(R.id.mapButton)
        val calendarButton = findViewById<Button>(R.id.calendarButton)


        calcButton.setOnClickListener(){
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }

        mapButton.setOnClickListener(){
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        calendarButton.setOnClickListener(){
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }


    }
}
