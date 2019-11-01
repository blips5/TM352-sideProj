package com.example.newappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var calcButton = findViewById<Button>(R.id.calcButton)


        calcButton.setOnClickListener(){
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)

        }


    }
}
