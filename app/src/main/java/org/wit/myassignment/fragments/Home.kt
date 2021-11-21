package org.wit.myassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.FragmentHomeBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber

lateinit var app: MainApp
private var _fragBinding: FragmentHomeBinding? = null
private val fragBinding get() = _fragBinding!!

class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        fragBinding.btnWorkouts.setOnClickListener{
            Timber.i("Button Pressed: ")
        }
        return super.onOptionsItemSelected(item)
    }




}