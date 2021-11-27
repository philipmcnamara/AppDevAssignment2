package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityBmiBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber
import kotlin.math.pow

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

    fun showResult(view: android.view.View) {

        var ageValue = Integer.parseInt(age.text as String)
        var weightValue = weight.text.toString().toDouble()
        var heightValue = height_value.text.toString().toDouble()/100


        if (weightValue >0.0 && heightValue >0.0)
        {
            var bmiValue = String.format("%.2f", weightValue/heightValue.pow(2))
            bmi.text =bmiValue

        }
        Timber.i("Bmi : ${bmi.text}")

        //showBMI(bmi)

    }


}


