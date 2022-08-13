package com.example.habitsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.habitsapp.presentation.screen.search.SearchScreen
import com.example.habitsapp.presentation.screen.add_habit.AddTodoScreen
import com.example.habitsapp.presentation.screen.completed.CompletedScreen
import com.example.habitsapp.presentation.screen.start.StartScreen
import com.example.habitsapp.presentation.screen.home.HomeScreen


@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController= navController)
        }


        composable(route = Screen.Completed.route) {
            CompletedScreen(navController = navController)
        }



        dialog(route = Screen.AddTodo.route) {
            AddTodoScreen(navController = navController)
        }

        searchTodoNavGraph(navController)

        composable(
            route = Screen.StartTodo.route,
            arguments = listOf(
                navArgument(
                    "itemId"
                ) {
                    type = NavType.IntType
                }
            )
        ) {
            StartScreen(navController = navController)
        }
    }
}



fun NavGraphBuilder.addTodoNavGraph(navController: NavHostController) {
    navigation(
        route = "details_route",
        startDestination = Screen.AddTodo.route
    ) {
        composable(route = Screen.AddTodo.route) {
            AddTodoScreen(navController = navController)
        }
    }
}




@ExperimentalComposeUiApi
fun NavGraphBuilder.searchTodoNavGraph(navController: NavHostController) {
    navigation(
        route = "search_route_graph", startDestination = Screen.Search.route)
            {
                composable(route = Screen.Search.route) {
                    SearchScreen(navController = navController)
                }

            }
}