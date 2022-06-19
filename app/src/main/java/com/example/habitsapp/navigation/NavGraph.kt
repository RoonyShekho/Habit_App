package com.example.habitsapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.habitsapp.presentation.screen.add_habit.AddTodoScreen
import com.example.habitsapp.presentation.screen.completed.CompletedScreen
import com.example.habitsapp.presentation.screen.start.StartScreen
import com.example.habitsapp.presentation.screen.home.HomeScreen


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

    /*    addTodoNavGraph(navController)

        startTodoNavGraph(navController)*/

             dialog(route = Screen.AddTodo.route) {
                  AddTodoScreen(navController = navController)
              }


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




fun NavGraphBuilder.startTodoNavGraph(navController: NavHostController) {
    navigation(
        route = "start_route_graph", startDestination = Screen.StartTodo.route,
        arguments = listOf(
            navArgument(
                "itemId",
            ) {
                type = NavType.IntType
            }
        )
    )
            {
                composable(route = Screen.StartTodo.route) {
                    StartScreen(navController = navController)
                }

            }
}