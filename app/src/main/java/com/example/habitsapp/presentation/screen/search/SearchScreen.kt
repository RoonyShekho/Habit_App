package com.example.habitsapp.presentation.screen.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.presentation.screen.components.TodoItem

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    vm: SearchViewModel = hiltViewModel()
) {


    val query by vm.query
    val state by vm.state

    val keyboardController  =LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            SearchTopBar(
                value = query,
                onValueChange = { vm.setQuery(it) },
                onSearchClick = { vm.searchHabits(query) }
            ) {
                if(query.isEmpty()){
                    keyboardController?.hide()
                }else{
                    vm.setQuery("")
                }
            }
        }
    ) {
        LazyColumn {
            items(state.todos) {
                TodoItem(
                    todo = it,
                    onDeleteClick = { vm.deleteTodo(it.id!!) },
                    navController = navController
                )
            }
        }

    }
}