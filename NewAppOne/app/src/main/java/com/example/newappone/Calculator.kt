package com.example.newappone

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder

class Calculator : AppCompatActivity() {








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)


        //Operator buttons
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonMult = findViewById<Button>(R.id.buttonMult)

        //button initialisation
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)


        //appends number or decimal string of button to the calculator progress when clicked.
        button0.setOnClickListener(){appendToExpression("0",true)}
        button1.setOnClickListener(){appendToExpression("1",true)}
        button2.setOnClickListener(){appendToExpression("2",true)}
        button3.setOnClickListener(){appendToExpression("3",true)}
        button4.setOnClickListener(){appendToExpression("4",true)}
        button5.setOnClickListener(){appendToExpression("5",true)}
        button6.setOnClickListener(){appendToExpression("6",true)}
        button7.setOnClickListener(){appendToExpression("7",true)}
        button8.setOnClickListener(){appendToExpression("8",true)}
        button9.setOnClickListener(){appendToExpression("9",true)}
        buttonDecimal.setOnClickListener(){appendToExpression(".", true)}

        //appends operators string to the calculator progress when clicked.
        buttonAdd.setOnClickListener(){appendToExpression("+", false)}
        buttonMinus.setOnClickListener(){appendToExpression("-", false)}
        buttonMult.setOnClickListener(){appendToExpression("*", false)}
        buttonDivide.setOnClickListener(){appendToExpression("/", false)}

        buttonDel.setOnClickListener(){
            calcProgress.text = ""
            calcResult.text = ""
        }

        buttonEquals.setOnClickListener(){

            try{
                val expression = ExpressionBuilder(calcProgress.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                {
                    calcResult.text = longResult.toString()
                    calcProgress.text = ""
                }
                else
                {
                    calcResult.text = result.toString()
                    calcProgress.text = ""
                }
            }
            catch (e:Exception){
                Log.d("Exception", " message")
            }
        }

    }
    fun appendToExpression(string : String, canClear : Boolean){
        if(canClear){
            calcResult.text = ""
            calcProgress.append(string)
        }
        else{
            calcProgress.append(calcResult.text)
            calcProgress.append(string)
            calcResult.text = ""
        }
    }
}
