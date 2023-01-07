package com.example.test


import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_main.*


class chart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var mList:List<SpendClass>
    var def: ColorStateList? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.test.R.layout.activity_chart)
        setSupportActionBar(materialToolbar)

        window.statusBarColor = ContextCompat.getColor(this, R.color.cardColor)

        def = item2.textColors;
        mList = listOf(SpendClass(com.example.test.R.drawable.instagram,"Instagram","Jan 12,2022","150.00"),
            SpendClass(com.example.test.R.drawable.discord,"Discord","Yesterday","85.00"),
            SpendClass(com.example.test.R.drawable.telegram,"Telegram","Jan 16,2022","11.99")
            )
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initLineChartDownFill()
        recyclerView = findViewById(com.example.test.R.id.spendRecycler)
        adapter = RecyclerAdapter(mList,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initLineChartDownFill() {
        lineChart.setTouchEnabled(false)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.setPinchZoom(false)
        lineChart.setDrawGridBackground(false)
        lineChart.maxHighlightDistance = 200F
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)
        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.isEnabled = false
        lineChart.xAxis.setDrawAxisLine(false);
        lineChart.extraBottomOffset = 10f
        lineChartDownFillWithData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun lineChartDownFillWithData() {

        val xAxisLabels = listOf("Mar", "Apr", "May", "June", "July", "Aug","Sep","Oct","Nov","Dec","","")
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.textColor = ContextCompat.getColor(this,R.color.textColor)
        lineChart.description.isEnabled = false
        val entryArrayList: ArrayList<com.github.mikephil.charting.data.Entry> = ArrayList()
        entryArrayList.add(com.github.mikephil.charting.data.Entry(0f, 20f, "1"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(1f, 30f, "2"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(2f, 25f, "3"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(3f, 30f, "4"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(4f, 35f, "5"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(5f, 40f, "6"))
        entryArrayList.add(com.github.mikephil.charting.data.Entry(6f, 30f, "7"))

        //LineDataSet is the line on the graph
        val lineDataSet = LineDataSet(entryArrayList, "This is y bill")
        lineDataSet.lineWidth = 2f
        lineDataSet.color = getColor(com.example.test.R.color.graph)
        lineDataSet.circleHoleColor = Color.GREEN
        lineDataSet.setCircleColor(R.color.white)
        lineDataSet.highLightColor = Color.RED
        lineDataSet.setDrawValues(false)
        lineDataSet.circleRadius = 10f
        lineDataSet.setCircleColor(Color.YELLOW)

        //to make the smooth line as the graph is adrapt change so smooth curve
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        //to enable the cubic density : if 1 then it will be sharp curve
        lineDataSet.cubicIntensity = 0.2f

        //to fill the below of smooth line in graph
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.BLACK
        //set the transparency
        lineDataSet.fillAlpha = 80

        //set the gradiant then the above draw fill color will be replace
        val drawable = ContextCompat.getDrawable(this,com.example.test.R.drawable.fade_green)
        lineDataSet.fillDrawable = drawable

        //set legend disable or enable to hide {the left down corner name of graph}
        val legend: Legend = lineChart!!.legend
        legend.isEnabled = false

        //to remove the cricle from the graph
        lineDataSet.setDrawCircles(false)

        //lineDataSet.setColor(ColorTemplate.COLORFUL_COLORS);
        val iLineDataSetArrayList: ArrayList<ILineDataSet> = ArrayList()
        iLineDataSetArrayList.add(lineDataSet)

        //LineData is the data accord
        val lineData = LineData(iLineDataSetArrayList)
        lineData.setValueTextSize(13f)
        lineData.setValueTextColor(Color.BLACK)
        lineChart!!.data = lineData
        lineChart!!.invalidate()
    }
}