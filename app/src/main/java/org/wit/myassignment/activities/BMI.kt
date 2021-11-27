package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_bmi.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityBmiBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber

class BMI : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBmiBinding
    private var seekBar: SeekBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        height_value

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }



    fun increaseAge(view: android.view.View) {

        if (Integer.parseInt(age.text.toString()) > 0){
            age.text = (Integer.parseInt(age.text as String)).plus(1).toString()
        }
    }

    fun decreaseAge(view: android.view.View) {
        Timber.i("Decrease Button Pressed:")
        if (Integer.parseInt(age.text.toString()) > 0){
            age.text = (Integer.parseInt(age.text as String)).minus(1).toString()
            Timber.i("Age : ${age.text}")
        }
    }

    fun decreaseWeight(view: android.view.View) {
        Timber.i("decreaseWeight Button Pressed:")
        if (Integer.parseInt(weight.text.toString()) > 0){
            weight.text = (Integer.parseInt(weight.text as String)).minus(1).toString()
            Timber.i("Weight : ${weight.text}")
        }
    }


    fun increaseWeight(view: android.view.View) {
        Timber.i("IncreaseWeight Button Pressed:")
        if (Integer.parseInt(weight.text.toString()) > 0){
            weight.text = (Integer.parseInt(weight.text as String)).plus(1).toString()
            Timber.i("Weight : ${weight.text}")
        }
    }

}


