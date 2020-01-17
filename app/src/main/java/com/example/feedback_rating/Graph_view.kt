package com.example.feedback_rating

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LabelFormatter
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.HashMap
//useless

class Graph_view : AppCompatActivity(),LabelFormatter {
    override fun setViewport(viewport: Viewport?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_view)
        setContentView(R.layout.activity_graph_view)
        var graph= findViewById<GraphView>(R.id.graph)
        var series= BarGraphSeries(arrayOf())
        //getting series from main activity
        var sub = intent.getSerializableExtra("sub") as HashMap<String,Float>
        sub.toSortedMap()// converted to sorted map
        graph.getLegendRenderer().setSpacing(10)
        graph.getLegendRenderer().setPadding(10)
        series.setColor(Color.GREEN)//here we failed as this graph does not support to color each datapoint
        graph.gridLabelRenderer.labelFormatter//for formatLabel but failed
        //adding data values to the series
        for((key,value) in sub)
            {
                var i=1.0
                if(key=="A")
                { series.appendData(DataPoint(i, value.toDouble()), false, 5)
                }
                else if(key=="B")
                { series.appendData(DataPoint(i+10, value.toDouble()), false, 5)
                    series.setColor(Color.BLACK)
                }
                else if(key=="C")
                { series.appendData(DataPoint(i+20, value.toDouble()), false, 5)
                }
                else if(key=="D")
                { series.appendData(DataPoint(i+30, value.toDouble()), false, 5)
                }
                else if(key=="E")
                {
                    series.appendData(DataPoint(i+40, value.toDouble()), false, 5)
                }

                //Toast.makeText(this,"${key.toString()+" "+value.toString()}",Toast.LENGTH_SHORT).show()
            }

        graph.addSeries(series)
    }
    //tried to set string values to the axis and failed as don't know how to call this method and we can call this like raintgbarchanged --> but don't know how to?
    override fun formatLabel(value: Double, isValueX: Boolean): String {
        Toast.makeText(this,"shanu",Toast.LENGTH_SHORT).show()

        if(isValueX)
        {
            return formatLabel(value,isValueX)

        }
        else
        {
            return formatLabel(value,isValueX)
        }
    }


}
