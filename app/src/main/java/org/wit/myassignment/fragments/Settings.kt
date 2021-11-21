package org.wit.myassignment.fragments

import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.preference.PreferenceFragmentCompat
import org.wit.myassignment.R

class Settings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        Toast.makeText(context, "These are your settings", Toast.LENGTH_SHORT).show()
    }

}