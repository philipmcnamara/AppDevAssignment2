package org.wit.myassignment.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.activity_weight_tracker.*
import org.wit.myassignment.R
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class WeightTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_tracker)

        val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10)
        val series1Number = arrayOf<Number>(1,4,8,16,32,26,29,10,13)

        val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* series1Number), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Serires 1")

        val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)

        plot.addSeries(series1,series1Format)

        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer?,
                pos: FieldPosition?
            ): StringBuffer? {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo?.append(domainLabels[i])
            }

            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                return null
            }

        }
        PanZoom.attach(plot)
    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}

