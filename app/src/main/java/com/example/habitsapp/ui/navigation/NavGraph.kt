@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.habitsapp.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.dialog
import com.example.habitsapp.ui.presentation.screen.add_habit.AddTodoScreen
import com.example.habitsapp.ui.presentation.screen.completed.CompletedScreen
import com.example.habitsapp.ui.presentation.screen.home.HomeScreen
import com.example.habitsapp.ui.presentation.screen.search.SearchScreen
import com.example.habitsapp.ui.presentation.screen.start.StartScreen
import com.example.habitsapp.util.Utility.TWEEN_DURATION
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SetupNavGraph(navController: NavHostController, finish: () -> Unit, startService: () -> Unit) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {


        navigation(
            startDestination = BottomBarScreen.Home.route,
            route = Screen.Home.route
        ) {
            home(navController = navController, finish)
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
            ),

            deepLinks = listOf(
                navDeepLink {
                    uriPattern = ""
                }
            ),
            enterTransition = {
                fadeIn(animationSpec = tween(TWEEN_DURATION))
            }
        ) {
            StartScreen(navController = navController, startService = startService)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
fun NavGraphBuilder.home(navController: NavHostController, finish: () -> Unit = {}) {

    composable(route = BottomBarScreen.Home.route) {
        HomeScreen(navController = navController)
        BackHandler {
            finish()
        }
    }


    composable(route = BottomBarScreen.Completed.route) {
        CompletedScreen(navController = navController)
    }
}


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.searchTodoNavGraph(navController: NavHostController) {
    navigation(
        route = "search_route_graph", startDestination = Screen.Search.route
    )
    {
        composable(route = Screen.Search.route, enterTransition = {
            fadeIn(animationSpec = tween(TWEEN_DURATION))
        }) {
            SearchScreen(navController = navController)
        }

    }
}