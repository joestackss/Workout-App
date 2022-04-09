package com.olamide.workout7minuteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.olamide.workout7minuteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private var myBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(myBinding?.root)



        myBinding?.frameslayout?.setOnClickListener{
           // Toast.makeText(this, "You just made FrameLayout Work", Toast.LENGTH_LONG)

            val intent = Intent (this, ExerciseActivity::class.java )
            startActivity(intent)
        }

        //TODO(Step 8 : Adding a click event to the BMI calculator button and navigating it to the BMI calculator feature.)
        //START
        myBinding?.flBMI?.setOnClickListener {
            // Launching the BMI Activity
            val intent = Intent(this, BMIActivity1::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //Todo 4: TO avoid memory leak we must unassign the binding once the activity is destroyed
        myBinding = null
    }
}