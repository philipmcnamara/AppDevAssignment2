package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidplot.xy.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.weight_tracker.*
import kotlinx.android.synthetic.main.weights.*
import org.wit.myassignment.R
import org.wit.myassignment.adapters.WeightAdapter
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.ui.data.WeightData
import timber.log.Timber
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*


class WeightTracker : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    var weights = ArrayList<WeightData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_tracker)

        database = FirebaseDatabase.getInstance().getReference("weightData").child("weights")

        val fireData = arrayListOf<WeightData>()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    val product = productSnapshot.getValue(WeightData::class.java)
                    fireData.add(product!!)
                }

                var daysTracked = arrayListOf<Int>()
                val weightList = arrayListOf<Int>()
                var increment = 0

                for (it in fireData){
                    daysTracked.add(it.dayOfMeasurement!!.toInt())
                    weightList.add(it.currentWeight!!.toInt())

                    increment = ++increment
                   // System.out.println(aysTracked)

                }

                System.out.println(daysTracked)
                System.out.println(weightList)

            }


            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })



        val daysTracked = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10)
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
        val intent = Intent(this, WeightList::class.java)
        startActivity(intent)
        finish()
    }
}
