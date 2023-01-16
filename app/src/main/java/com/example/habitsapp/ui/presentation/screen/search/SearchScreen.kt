package com.example.habitsapp.ui.presentation.screen.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.ui.navigation.Screen
import com.example.habitsapp.ui.presentation.screen.components.TodoItem
import com.example.habitsapp.util.Utility.navigateTo

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    vm: SearchViewModel = hiltViewModel()
) {

    val query by vm.query
    val state by vm.state
    val scaffoldState = rememberScaffoldState()

    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(key1 = query){
        if(query.isEmpty()){
            //TODO:
        }
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                value = query,
                onValueChange = {
                    vm.setQuery(it)
                    vm.searchHabits(query)
                                },
                onSearchClick = { vm.searchHabits(query) }
            ) {
                if(query.isEmpty()){
                    keyboardController?.hide()
                    navigateTo(Screen.Home.route, navController)
                }else{
                    vm.setQuery("")
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(state.habits) {
                TodoItem(
                    todo = it,
                    onDeleteClick = { vm.deleteTodo(it.id!!) },
                    navController = navController
                )
            }
        }

    }
}