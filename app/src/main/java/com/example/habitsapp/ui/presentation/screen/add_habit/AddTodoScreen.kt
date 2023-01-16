package com.example.habitsapp.ui.presentation.screen.add_habit


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.FullHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.example.habitsapp.ui.navigation.Screen
import com.example.habitsapp.util.Utility.hoursToMs
import com.example.habitsapp.util.Utility.isNumberValid
import com.example.habitsapp.util.Utility.minutesToMs
import com.example.habitsapp.util.Utility.showToastMessage


@ExperimentalMaterial3Api
@Composable
fun AddTodoScreen(
    vm: AddTodoViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state = vm.state.value


    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.height(400.dp)
            .background(Color.White),
        floatingActionButton = {
            IconButton(
                onClick = {
                    if (isNumberValid(
                            state.hours.toInt(),
                            state.minutes.toInt()
                        ) && state.title.isNotEmpty()
                    ) {
                        vm.onAction(AddTodoAction.Add)
                        navController.navigate(Screen.Home.route)
                    } else {
                        showToastMessage(
                            "Please choose valid values for time and title.",
                            context
                        )
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "save todo")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {
                    vm.onAction(AddTodoAction.EnteredTitle(it))
                },
                label = { Text(text = "Title..") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )


            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                NumPicker(onHoursChange = {
                    vm.onAction(AddTodoAction.EnteredHours(it))
                }, onMinutesChange = {
                    vm.onAction(AddTodoAction.EnteredMinutes(it))
                })
            }
        }
    }
}

@Composable
fun NumPicker(
    onHoursChange: (Long) -> Unit,
    onMinutesChange: (Long) -> Unit
) {

    var pickerValue by remember { mutableStateOf<Hours>(FullHours(0, 1)) }

    HoursNumberPicker(
        dividersColor = MaterialTheme.colorScheme.primary,
        leadingZero = false,
        value = pickerValue,
        onValueChange = {
            onHoursChange(hoursToMs(it.hours))
            onMinutesChange(minutesToMs(it.minutes))
            pickerValue = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.size(24.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        }
    )
}