package com.example.feedback_rating

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.beardedhen.androidbootstrap.AwesomeTextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.lang.NullPointerException

class MPGraph_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpgraph_view)
        var BarChart=findViewById<BarChart>(R.id.chart)
        BarChart.getDescription().setEnabled(false)
        BarChart.getAxisLeft().setDrawGridLines(false)
        BarChart.getXAxis().setDrawGridLines(false)
        BarChart.setDrawGridBackground(false)
        var id=findViewById<TextView>(R.id.id)
        var intent2:String? =intent?.getStringExtra("ID")
        id.text="Student ID: $intent2"
        id.setTextColor(Color.MAGENTA)


        var sub = intent.getSerializableExtra("sub") as HashMap<String,Float>//getting hash map from main activity
        sub.toSortedMap()//sorted in descnding order think so
        var dataVals=ArrayList<BarEntry>()//contain BarEntry(x,y) objects
        for((key,value)in sub)
        {
            if(key=="Java"){dataVals.add(BarEntry(0.0F,value))}
            else if(key=="Webtech"){dataVals.add(BarEntry(1.0F,value))}
            else if(key=="Datamining"){dataVals.add(BarEntry(2.0F,value))}
            else if(key=="Android programming"){dataVals.add(BarEntry(3.0F,value))}
            else if(key=="CPP"){dataVals.add(BarEntry(4.0F,value))}
        }

       // fun datavalues1():ArrayList<BarEntry> //{ dataVals.add(BarEntry(20.toFloat(),2.toFloat()))//dataVals.add(BarEntry(40.toFloat(),5.toFloat())) //return dataVals }

        var bardataset1= BarDataSet(dataVals,"shanu")// collection of BarEntry(x,y) into set
        var datasets:ArrayList<IBarDataSet> = ArrayList()//for collecting datasets
        datasets.add(bardataset1)//adding datasets
        var data= BarData(datasets)//adding all datasets to bar data
        BarChart.setData(data)//setting data
        BarChart.invalidate()
        var colors_array= arrayListOf<Int>(Color.BLACK,Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN)//ArrayList<Int>() is used when we need to add values dynamically
        bardataset1.setColors(colors_array)//setting colors for each data value (x,y)

        //legend lables
        var l:Legend=BarChart.legend
        //LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)),//LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)), //LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)), //LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)), //LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)) //var legends= arrayOf<LegendEntry>() //var legends= arrayOfNulls<LegendEntry>(5) //legends.set(0,LegendEntry("shnau",Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(2)))
        var legends= mutableListOf<LegendEntry>()
        var i=0
        for((key,value) in sub)
        {   //adding legends dynamically to the the corresponding colors
            legends.add(i,LegendEntry(key,Legend.LegendForm.CIRCLE,10f,2f,null,colors_array.get(i)))
            i++

        }

        l.setCustom(legends)

        l.isEnabled=true

    }
}
