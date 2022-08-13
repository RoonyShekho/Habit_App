package com.example.habitsapp.presentation.screen.start


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.navigation.Screen
import com.example.habitsapp.presentation.screen.components.CountDownButton
import com.example.habitsapp.presentation.screen.components.CountDownIndicator
import com.example.habitsapp.ui.theme.backgroundColor
import com.example.habitsapp.ui.theme.textColor
import kotlinx.coroutines.delay


@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    navController: NavHostController
) {


    val time by viewModel.time.observeAsState()

    val progress = viewModel.progressState.observeAsState()


    val showDialog = viewModel.showDialog.observeAsState()

    val isCompleted = viewModel.isCompleted.observeAsState()

    val context = LocalContext.current


    BackHandler {
        viewModel.onAction(StartAction.ShowDialog)
    }


    if (isCompleted.value!!) {
        LaunchedEffect(key1 = true) {
            navController.navigate(Screen.Home.route)
            navController.popBackStack()
            delay(2500)
        }
        viewModel.onAction(StartAction.Delete)
    }

    Box(Modifier.background(MaterialTheme.colors.backgroundColor)) {
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
            color = MaterialTheme.colors.textColor,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        )


        Text(
            text = "Click to start or stop countdown",
            color = MaterialTheme.colors.textColor,
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