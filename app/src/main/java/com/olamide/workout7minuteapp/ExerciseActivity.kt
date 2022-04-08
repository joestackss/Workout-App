package com.olamide.workout7minuteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olamide.workout7minuteapp.databinding.ActivityExerciseBinding


class ExerciseActivity : AppCompatActivity() {
    private var binding : ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Todo 4: then set support action bar and get toolBarExcerciser using the binding
        //variable

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}