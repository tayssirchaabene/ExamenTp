package com.example.imcproject.ui.home

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun AnalysisGraph(imcData: List<Float>) {
   /* val entries = imcData.mapIndexed { index, imc ->
        SaveableStateRegistry.Entry(
            index.toFloat(),
            imc
        )
    }
    val dataSet = LineDataSet(entries, "IMC")
    dataSet.color = Color.BLUE
    dataSet.circleRadius = 5f
    dataSet.setCircleColor(Color.RED)

    val lineData = LineData(dataSet)

    AndroidView(factory = { context ->
        LineChart(context).apply {
            this.data = lineData
            this.description.text = "Évolution de l'IMC"
            this.animateX(1000)
        }
    })*/
}
