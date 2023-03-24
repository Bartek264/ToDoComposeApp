package com.example.todocomposeapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocomposeapp.navigation.ScreenConst.LIST_SCREEN

fun NavGraphBuilder.listComposable() {
	composable(route = LIST_SCREEN, arguments = listOf(navArgument()))
}