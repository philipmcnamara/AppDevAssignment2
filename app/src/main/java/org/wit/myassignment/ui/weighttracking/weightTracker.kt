package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.activity_weight_tracker.*
import org.wit.myassignment.R
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.WeightModel
import timber.log.Timber
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*


class weightTracker : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_tracker)

        lateinit var app: MainApp


        val daysTracked = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10)
        val weightList = arrayOf<Number>(84,82,81,83,86,85,81,79,75)


        val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* weightList), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Days Tracked")

        val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)

        plot.addSeries(series1,series1Format)

        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer?,
                pos: FieldPosition?
            ): StringBuffer? {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo?.append(daysTracked[i])
            }

            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                return null
            }

        }
        PanZoom.attach(plot)
    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, RoutineActivity::class.java)
        startActivity(intent)
        finish()
    }
}


