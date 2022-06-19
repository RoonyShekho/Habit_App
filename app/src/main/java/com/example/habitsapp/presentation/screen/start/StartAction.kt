package com.example.habitsapp.presentation.screen.start

sealed class StartAction{
    object Delete:StartAction()
    object ShowDialog:StartAction()
    object HideDialog:StartAction()
}
