package com.example.habitsapp.ui.presentation.screen.start

sealed class StartAction{
    object Delete:StartAction()
    object ShowDialog:StartAction()
    object HideDialog:StartAction()
}
