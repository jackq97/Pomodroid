package com.example.pomodoro.screen.infoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodoro.ui.composables.InfoColumn
import com.example.pomodoro.ui.composables.InfoTotalColumn
import com.example.pomodoro.ui.composables.PieChart
import com.example.pomodoro.ui.composables.radiobuttons.LineRadioButtons
import com.example.pomodoro.ui.composables.radiobuttons.PieRadioButtons
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.model.LineData
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InfoScreen(){

    val lineChartData = remember {
        mutableStateListOf(
            LineData("sun", 12F),
            LineData("mon", 20F),
            LineData("tue", 50F),
            LineData("wed",200F),
            LineData("thur", 10F),
            LineData("fri", 8F),
            LineData("sat", 8F),
        )
    }

    var selectedPieRadioOption by remember {
        mutableStateOf("Day")
    }

    var selectedLineRadioOption by remember {
        mutableStateOf("Week")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)) {

        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

            Row {

                InfoColumn(modifier = Modifier
                    .padding(start = 10.dp),
                    label = "Today's Pomo",
                    progress = "1 from yesterday",
                    value = "1")

                InfoColumn(modifier = Modifier
                    .padding(start = 10.dp,
                    end = 10.dp),
                    label = "Today's focus (h)",
                    progress = "0h13m from yesterday",
                    value = "0h13m")
            }

            Row(modifier = Modifier
                .padding(top = 12.dp)) {

                InfoTotalColumn(modifier = Modifier
                    .padding(start = 10.dp),
                    label = "Total Pomos",
                    value = "20")

                InfoTotalColumn(modifier = Modifier
                    .padding(start = 10.dp,
                        end = 10.dp),
                    label = "Total Focus Duration",
                    value = "9h13m")
            }

            // TODO: this box is reusable make it a function
            Box(modifier = Modifier
                .padding(
                    top = 12.dp,
                    start = 10.dp,
                    end = 10.dp
                )
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .height(270.dp)
                .background(Color.DarkGray)) {

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    PieRadioButtons {
                        selectedPieRadioOption = it
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PieChart()
                }
            }

            Box(modifier = Modifier
                .padding(
                    top = 12.dp,
                    start = 10.dp,
                    end = 10.dp
                )
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .height(280.dp)
                .background(Color.DarkGray)) {

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LineRadioButtons {
                        selectedLineRadioOption = it
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    
                    LineChart(
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                        lineData = lineChartData
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun InfoScreenPreview(){

    InfoScreen()
}
