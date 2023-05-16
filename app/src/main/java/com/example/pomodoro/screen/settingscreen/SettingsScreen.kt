package com.example.pomodoro.screen.settingscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pomodoro.model.local.Settings
import com.example.pomodoro.ui.composables.SettingsSliderText
import com.example.pomodoro.ui.composables.SettingsText
import com.example.pomodoro.ui.composables.SliderComponent
import com.example.pomodoro.util.floatToTime
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    viewModel: SettingsViewModel
) {

    var focusSliderPosition by remember { mutableStateOf(0f) }
    var breakSliderPosition by remember { mutableStateOf(0f) }
    var longBreakSliderPosition by remember { mutableStateOf(0f) }
    var noOfRoundsSliderPosition  by remember { mutableStateOf(0f) }

    val settings = viewModel.settings.collectAsState()

    focusSliderPosition = settings.value.focusDur
    breakSliderPosition = settings.value.restDur
    longBreakSliderPosition = settings.value.longRestDur
    noOfRoundsSliderPosition = settings.value.rounds

    Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(modifier = Modifier.padding(top = 8.dp),
                    text = "Timer",
                    fontSize = 25.sp
                )

                SettingsText(label = "Focus")
                SettingsSliderText(label = floatToTime(focusSliderPosition).toString())

                SliderComponent(
                    value = focusSliderPosition,
                    onValueChange = {
                        focusSliderPosition = it
                    },
                    minValue = 1f,
                    maxValue = 10f
                )

                SettingsText(label = "Short Break")
                SettingsSliderText(label = floatToTime(breakSliderPosition).toString())

                SliderComponent(
                    value = breakSliderPosition,
                    onValueChange = {
                        breakSliderPosition = it
                    },
                    minValue = 1f,
                    maxValue = 10f
                )

                SettingsText(label = "Long Break")
                SettingsSliderText(label = floatToTime(longBreakSliderPosition).toString())

                SliderComponent(
                    value = longBreakSliderPosition,
                    onValueChange = {
                        longBreakSliderPosition = it
                    },
                    minValue = 1f,
                    maxValue = 10f
                )

                SettingsText(label = "Rounds")
                SettingsSliderText(label = floatToTime(noOfRoundsSliderPosition).toString())

                SliderComponent(
                    value = noOfRoundsSliderPosition,
                    onValueChange = {
                        noOfRoundsSliderPosition = it
                    },
                    minValue = 1f,
                    maxValue = 10f
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    Button(
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                        viewModel.saveSettings(
                            Settings(focusDur = focusSliderPosition,
                            restDur = breakSliderPosition,
                            longRestDur = longBreakSliderPosition,
                            rounds = noOfRoundsSliderPosition

                        )) }){

                        Text(text = "save data")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    TextButton(modifier = Modifier,
                        onClick = { /*TODO*/ }) {
                        Text(text = "Reset Defaults")
                    }
                }

            }
        }
}

@Preview
@Composable
fun SettingsPreview(){

    //SettingsScreen(navigator = EmptyDestinationsNavigator)
}