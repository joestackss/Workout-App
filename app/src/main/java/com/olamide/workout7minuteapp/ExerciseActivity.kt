package com.olamide.workout7minuteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.olamide.workout7minuteapp.databinding.ActivityExerciseBinding


class ExerciseActivity : AppCompatActivity() {

    //TODO(Step 1 - Adding a variables for the 10 seconds REST timer.)

    //START
    // Variable for USER to Rest and later on we will initialize it.
    private var restTimer: CountDownTimer? = null

    // Variable for timer progress. How far we've come. As initial value the rest progress is set to 0. As we are about to start.
    private var restProgress = 0
    //END

    // TODO(Step 2 - Adding a variables for the 30 seconds Exercise timer.)
    // START

    // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseTimer: CountDownTimer? = null

    // Variable for the exercise timer progress. As initial value the exercise progress is set to 0.
    // As we are about to start.
    private var exerciseProgress = 0
    // END

    private var exerciseTimerDuration : Long = 30
    // TODO(Step 6 - The Variable for the exercise list and current position of exercise here it is -1 as the list starting element is 0.)
    // START
    private var exerciseList: ArrayList<ExerciseModel>? = null // We will initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.
    // END
    // create a binding variable

    private var binding : ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Todo 4: then set support action bar and get toolBarExercise using the binding
        //variable

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        // TODO(Step 7 - Initializing and Assigning a default exercise
        //  list to our list variable.)
        // START
        exerciseList = Constants.defaultExerciseList()
        // END

        //TODO(Step 4 - Calling the function to make it visible on screen.)-->
        //START
        setupRestView() // REST View is set in this function
        //END
    }

    //TODO(Step 3 - Setting up the Get Ready View with 10 seconds of timer.)-->
    //START
    /**
     * Function is used to set the timer for REST.
     */
    private fun setupRestView() {

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.upcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE

        /**
         * Here firstly we will check if the timer is running, or
         * we go back to previous page, and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        // TODO (Step 2 - Setting the upcoming exercise name in the UI element.)
        // START
        // Here we have set the upcoming exercise name to the text view
        // Here as the current position is -1 by default so to selected from the list it should be 0 so we have increased it by +1.
        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()
        // This function is used to set the progress details.

        // This function is used to set the progress details.
        setRestProgressBar()
    }
    // END

    //TODO(Step 2 - Setting up the 10 seconds timer for rest view and updating it continuously.)-->
    //START
    /**
     * Function is used to set the progress of timer using the progress
     */

    private fun setRestProgressBar() {

        // Sets the current progress to the specified value.
        binding?.progressBar?.progress = restProgress


        /**
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased by 1
                binding?.progressBar?.progress = 10 - restProgress // Indicates progress bar progress
                binding?.tvTimer?.text =
                    (10 - restProgress).toString()  // Current progress is set to text view in terms of seconds.
            }

            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                // TODO(Step 5 - After completing 10 Seconds of the REST timer
                //  start the 30 seconds of Start Exercise View.)
                // START
                currentExercisePosition++
                setupExerciseView()
            }
        }.start()
    }
    //END


    // TODO(Step 4 - Setting up the Exercise View with a 30 seconds timer.)
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View.
     */
    private fun setupExerciseView() {

        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        /**
         * Here firstly we will check if the timer is running and it is not null then cancel the running timer and start the new one.
         * And set the progress to the initial value which is 0.
         */
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        // TODO(Step 9 - Setting up the current exercise name and image to view to the UI element.)
        // START
        /**
         * Here current exercise name and image is set to exercise view.
         */
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        // END

        setExerciseProgressBar()
    }
    // END




    // TODO(Step 3 - After REST View Setting up the 30 seconds timer for the Exercise view and updating it continuously.)
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View for 30 Seconds
     */
    private fun setExerciseProgressBar() {

        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000,  1000) {

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                // Updating the view after completing the 30 seconds exercise
                // START
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    setupRestView()
                } else {

                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // END
            }
        }.start()
    }
    // END


    //TODO(Step 5 - Destroying the timer when closing the activity or app.)-->
    //START
    /**
     * Here in the Destroy function we will reset the rest timer if it is running.
     */
    public override fun onDestroy() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        super.onDestroy()
        binding = null
    }
    //END
}