package com.example.atmcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var SDate : TextView? = null
    private var DateIM : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SDate = findViewById(R.id.SelectedDate)
        DateIM = findViewById(R.id.AIM)
        val btnDatePicker:Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val y = myCalendar.get(Calendar.YEAR)
        val m = myCalendar.get(Calendar.MONTH)
        val d = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { _, year, month, dayOfMonth ->
                val sd = "$dayOfMonth/${month+1}/$year"
                SDate?.text = sd
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(sd)
                theDate?.let{
                    val sdim = theDate.time / 60000
                    val cd = sdf.parse(sdf.format(System.currentTimeMillis()))
                    cd?.let{
                        val cdim = cd.time / 60000
                        val dim = cdim-sdim
                        DateIM?.text = dim.toString()
                    }
                }
            },
            y,
            m,
            d
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}