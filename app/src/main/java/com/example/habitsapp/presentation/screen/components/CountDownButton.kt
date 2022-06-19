package com.example.habitsapp.presentation.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountDownButton(
    isPlaying: Boolean,
    isCompleted:Boolean,
    optionSelected: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 90.dp))
    {

        Button(
            onClick = {
                optionSelected()
            },
            modifier =
            Modifier
                .height(50.dp)
                .width(140.dp),

            shape = RoundedCornerShape(25.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor =  Color.White,
            ),

            )

        {
            val pair = if (!isPlaying && !isCompleted) {
                "START"
            }

            else {
                "STOP"
            }

            Text(
                pair,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }


}