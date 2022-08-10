package ru.gb.kotlinmvp.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(login: String): Screen
}
