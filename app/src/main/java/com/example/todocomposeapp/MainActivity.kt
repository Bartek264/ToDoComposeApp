package com.example.todocomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocomposeapp.navigation.SetupNavigation
import com.example.todocomposeapp.ui.theme.ToDoComposeAppTheme

class MainActivity : ComponentActivity() {

	private lateinit var navController: NavHostController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ToDoComposeAppTheme {
				navController = rememberNavController()
				SetupNavigation(navController = navController)
			}
		}
	}
}