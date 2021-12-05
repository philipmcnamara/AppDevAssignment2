package org.wit.myassignment.ui.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_bmiresults.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityBmiBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber
import kotlin.math.pow

class BMI : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityBmiBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


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

    fun showResult(view: android.view.View) {

        var weightValue = weight.text.toString().toDouble()
        var heightValue = height_value.text.toString().toDouble()/100

        if (weightValue >0.0 && heightValue >0.0)
        {
            var bmiValue = String.format("%.2f", weightValue/heightValue.pow(2))
            bmi.text =bmiValue
            bmiStatus.text = bmiStatusValue(weightValue/heightValue.pow(2))
        }

        Timber.i("Bmi: ${bmi.text}")
        setContentView(R.layout.activity_bmiresults)
        showResult()
    }

    private fun showResult() {
        bmiResult.text = bmi.text as String
        bmiStatus2.text = bmiStatus.text as String
    }

    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if(bmi<18.5)
            bmiStatus = "Underweight"
        else if  (bmi>=18.5 && bmi <25)
            bmiStatus = "Normal"
        else if (bmi>=25 && bmi <30)
            bmiStatus = "Overweight"
        else if (bmi>30)
            bmiStatus = "Obese"
        return bmiStatus

    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, org.wit.myassignment.ui.home.Home::class.java)
        startActivity(intent)
        finish()
    }


}


