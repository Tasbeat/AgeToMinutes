package com.ali.tasbeat.agetominutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType", "SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerButton = findViewById<Button>(R.id.selected_age_button)
        val ageInNumberView = findViewById<TextView>(R.id.age_in_number)
        val ageInMinutes = findViewById<TextView>(R.id.age_in_minutes)
        val ageShow = findViewById<TextView>(R.id.age_show)
        val groupProperty = findViewById<TextView>(R.id.group_property)

        datePickerButton.setBackgroundColor(Color.BLUE)
        datePickerButton.setOnClickListener {
            var calender = Calendar.getInstance()
            var currentYear = calender.get(Calendar.YEAR)
            var currentMonth = calender.get(Calendar.MONTH)
            var currentDay = calender.get(Calendar.DAY_OF_MONTH)

            var datePicker = DatePickerDialog(this, 2 , DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

               if(year <= currentYear && month <= currentMonth && dayOfMonth <= currentDay){
               try {
                   var simpleDateFormatter = SimpleDateFormat("dd/mm/yyyy")
                   var selectedDateInMinutes  =  simpleDateFormatter.parse("${dayOfMonth}/${month+1}/${year}").time / 60000
                   var ageShowString = "${dayOfMonth}/${month+1}/${year}"
                   var ageInNumberShow = " ${ currentYear-year} year "
                   var rangeOfPersonAge = currentYear - year
                   var relatedGroupState = when(rangeOfPersonAge){
                       in 0..3 -> "1-3:Energy and vitality"
                       in 3..6 -> "3-6:Playful"
                       in 6..8 -> "6-8:Imagination"
                       in 9..11 -> "9-11:Innovation and creativity"
                       in 12..20 -> "12-20:enthusiasm"
                       in 20..35 -> "20-35:Effort and perseverance "
                       in 35..50 -> "35-50:Meditation and contemplation "
                       in 50..80 -> "50-80:Generosity and benevolence "
                       in 80..120 -> "80-120:Wisdom and virtue  "
                       else -> "NO PROPERTY"
                   }
                   groupProperty.text = relatedGroupState
                   ageInNumberView.text = ageInNumberShow
                   ageShow.text = ageShowString
                   ageShow.setTextColor(Color.argb(200, 1000, 100, 50))
                   var systemCurrentTimeInMinutes = simpleDateFormatter.parse(simpleDateFormatter.format(System.currentTimeMillis())).time / 60000
                   var selectedAgeInMinutesResult = systemCurrentTimeInMinutes - selectedDateInMinutes
                   ageInMinutes.text = "$selectedAgeInMinutesResult MIN"

                  }catch (ex:Exception){
                   Toast.makeText(this , "$ex Error", Toast.LENGTH_LONG).show()
                  }
               }
            }
                ,currentYear,currentMonth,currentDay
            )
            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }
    }
}