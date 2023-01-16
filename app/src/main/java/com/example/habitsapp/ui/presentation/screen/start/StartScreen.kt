package com.example.habitsapp.ui.presentation.screen.start


import android.media.RingtoneManager
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.ui.navigation.Screen
import com.example.habitsapp.ui.presentation.screen.components.CountDownButton
import com.example.habitsapp.ui.presentation.screen.components.CountDownIndicator
import kotlinx.coroutines.delay


@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    navController: NavHostController,
    startService: () -> Unit
) {


    val time by viewModel.time.observeAsState()

    val progress = viewModel.progressState.observeAsState()

    val showDialog = viewModel.showDialog.observeAsState()

    val isCompleted = viewModel.isCompleted.observeAsState()

    val context = LocalContext.current


    BackHandler {
        if(progress.value!!.isPlaying) {
            viewModel.onAction(StartAction.ShowDialog)
        }else{
            navController.navigate(Screen.Home.route)
        }
    }


    val ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val ringToneSound = RingtoneManager.getRingtone(context, ringToneUri)

    if (isCompleted.value!!) {
        LaunchedEffect(key1 = true) {
            ringToneSound.play()
            navController.navigate(Screen.Home.route)
            navController.popBackStack()
            delay(2500)
        }
        viewModel.onAction(StartAction.Delete)
    }

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        if (showDialog.value == true) {
            AlertDialogComponent(vm = viewModel) {
                navController.navigate(Screen.Home.route)

            }
        }

        LaunchedEffect(key1 = isCompleted.value) {
            if (isCompleted.value!!) {
                Toast.makeText(
                    context,
                    progress.value!!.title + "is completed!",
                    Toast.LENGTH_LONG
                ).show()

                navController.navigate(Screen.Home.route)
            }
        }

        StartScreenItem(
            time = time!!,
            progress = progress.value!!.progress,
            isPlaying = progress.value!!.isPlaying,
            title = progress.value!!.title,
            isCompleted = isCompleted.value!!
        ) {
            viewModel.handleCountDownTimer()
        }
    }

    if (isCompleted.value == false && progress.value!!.isPlaying) {
        startService()
    }
}


@Composable
fun StartScreenItem(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    title: String,
    isCompleted: Boolean,
    optionSelected: () -> Unit
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Habit name:${title}",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )


        Text(
            text = "Click to start or stop countdown",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        CountDownIndicator(
            Modifier.padding(top = 50.dp),
            progress = progress,
            time = time,
            size = 250,
            stroke = 12
        )

        CountDownButton(
            modifier = Modifier
                .padding(top = 70.dp)
                .size(70.dp),
            isPlaying = isPlaying,
            isCompleted = isCompleted
        ) {
            optionSelected()
        }
    }
}

@Composable
fun AlertDialogComponent(
    vm: StartViewModel,
    onConfirmClicked: () -> Unit
) {

    if (vm.showDialog.value == true) {

        AlertDialog(onDismissRequest = {
            vm.onAction(StartAction.HideDialog)
        },
            title = { Text(text = "Stop the habit") },
            text = { Text(text = "Are you sure that you want to go back?") },

            confirmButton = {
                Button(onClick = {
                    onConfirmClicked()
                    vm.onAction(StartAction.HideDialog)
                }) {
                    Text(text = "YES")
                }
            },

            dismissButton = {
                Button(onClick = { vm.onAction(StartAction.HideDialog) }) {
                    Text(text = "NO")
                }
            }
        )
    }
}
