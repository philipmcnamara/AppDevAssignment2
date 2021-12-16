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
import kotlin.collections.ArrayList


class WeightTracker : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    var fireData = ArrayList<WeightData>()
    var daysList = arrayListOf<Int>()
    var weightList = arrayListOf<Int>()
    var increment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_tracker)

        database = FirebaseDatabase.getInstance().getReference("weightData").child("weights")
        //var fireData = arrayListOf<WeightData>()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (productSnapshot in dataSnapshot.children) {
                    var data = productSnapshot.getValue(WeightData::class.java)
                    fireData.add(data!!)
                }
                //this fireData contains a full arrayList
                //System.out.println(fireData)
                // Graph will not work in here!!
                populateList(fireData)


            }
            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
            //Graph will work here but the Data will not get sent into it!
            //System.out.println(fireData)

           // val daysArray: Array<Int> = daysList.toTypedArray()
            //val weightArray: Array<Int> = weightList.toTypedArray()
            val daysArray = arrayListOf(1,2,3,4,5,6,7,8,9,10)
            val weightArray = arrayOf(84,82,81,83,86,85,81,79,75)
            //System.out.println(daysArray[0])
            // System.out.println(weightArray[1])


            val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* weightArray), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Days Tracked")

            val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)

            plot.addSeries(series1,series1Format)

            plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
                override fun format(
                    obj: Any?,
                    toAppendTo: StringBuffer?,
                    pos: FieldPosition?
                ): StringBuffer? {
                    val i = Math.round((obj as Number).toFloat())
                    return toAppendTo?.append(daysArray[i])
                }

                override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                    return null
                }
            }
            PanZoom.attach(plot)
    }


    fun populateList(fireData : ArrayList<WeightData>){
        //firebase data here is and empty array, Need to call the data from line 39
        for (it in fireData) {
            daysList.add(it.dayOfMeasurement!!.toInt())
           // weightList.add(it.currentWeight!!.toInt())
            //System.out.println(fireData)
        }
    }



    fun Home(view: android.view.View) {
        val intent = Intent(this, WeightList::class.java)
        startActivity(intent)
        finish()
    }
}
