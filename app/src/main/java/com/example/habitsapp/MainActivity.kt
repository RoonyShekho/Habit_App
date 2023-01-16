@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.habitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.NotificationManagerCompat
import com.example.habitsapp.ui.navigation.SetupNavGraph
import com.example.habitsapp.ui.theme.BlueTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                HabitsApp(finish = {finish()}) {

              /*      Intent(applicationContext, CountDownService::class.java).apply {
                        action = CountDownService.ACTION_START
                        startService(this)
                    }*/
                }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun HabitsApp(finish: () -> Unit, startService: () -> Unit) {
    BlueTheme {
        val navController = rememberAnimatedNavController()
        SetupNavGraph(
            navController = navController, finish = { finish() }, startService = startService
        )
    }
}



