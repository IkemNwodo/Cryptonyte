package com.ikem.nwodo.cryptonyte.binding

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.PictureDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.EntryXComparator
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.db.model.History
import com.ikem.nwodo.cryptonyte.utils.graph.MyXAxisValueFormatter
import com.ikem.nwodo.cryptonyte.utils.graph.MyYAxisValueFormatter
import com.ikem.nwodo.cryptonyte.utils.graph.NumberFormatter
import com.ikem.nwodo.cryptonyte.utils.svg.SvgSoftwareLayerSetter
import java.util.*
import kotlin.collections.ArrayList

object BindingAdapters {


    @JvmStatic
    @BindingAdapter("imageBackground")
    fun setImageViewBackground(imageView: ImageView, coinColor: String?){
        val drawable = ContextCompat.getDrawable(imageView.context, R.drawable.coin_icon_drawable)

        val gradientDrawable = drawable as GradientDrawable
        if (coinColor?.length == 4 || coinColor == null){
            gradientDrawable.setStroke(4, Color.parseColor("#000000"))
            gradientDrawable.setColor(Color.parseColor("#000000"))
        } else{
            gradientDrawable.setStroke(4, Color.parseColor(coinColor))
            gradientDrawable.setColor(Color.parseColor(coinColor))
        }
        imageView.background = gradientDrawable

    }

    @JvmStatic
    @BindingAdapter("imageResource")
    fun setImage(imageView: ImageView, iconUrl: String?){
        Glide.with(imageView.context)
                .`as`(PictureDrawable::class.java)
                .listener(SvgSoftwareLayerSetter())
                .load(iconUrl)
                .into(imageView)


    }


    @JvmStatic
    @BindingAdapter("app:setChartData")
    fun setChartData(lineChart: LineChart, coinHistory: List<History>?){

        lineChart.description.isEnabled = false

        // enable touch gestures
        lineChart.setTouchEnabled(true)

        lineChart.dragDecelerationFrictionCoef = 0.9f

        // enable scaling and dragging
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.setDrawGridBackground(false)
        lineChart.isHighlightPerDragEnabled = true


        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)

        val  l = lineChart.legend
        l.isEnabled = false

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = Color.WHITE
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.rgb(255, 192, 56)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 1f // one hour
        xAxis.labelCount = 3
        xAxis.valueFormatter = MyXAxisValueFormatter()

        val leftAxis = lineChart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.setDrawGridLines(true)
        leftAxis.setDrawAxisLine(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.granularity = 500f
        leftAxis.labelCount = 3
        leftAxis.yOffset = -9f
        leftAxis.textColor = Color.rgb(255, 192, 56)
        leftAxis.valueFormatter = MyYAxisValueFormatter()

        val rightAxis = lineChart.axisRight
        rightAxis.isEnabled = false

        lineChart.data = getData(coinHistory)

    }

    private fun getData(coinHistory: List<History>?): LineData? {
        val entries = ArrayList<Entry>()

        //Log.i("Coin History", "${coinHistory == null}")
        if (coinHistory != null) {
            for (i in 0..coinHistory.size) {
                val history = coinHistory[i]
                val minute = history.timestamp as Float
                val historyPrice = history.price
                val price = historyPrice as Float
                entries.add(Entry(minute, price))
                Log.i("Graph data", "X: $minute Y: $price");
            }
        }

        Collections.sort(entries, EntryXComparator())

        // create a dataset and give it a type
        val set1 = LineDataSet(entries, "DataSet 1");
        set1.axisDependency = YAxis.AxisDependency.LEFT;
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

        return data

    }
}