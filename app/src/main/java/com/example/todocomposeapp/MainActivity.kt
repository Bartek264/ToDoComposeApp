package com.example.todocomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocomposeapp.navigation.SetupNavigation
import com.example.todocomposeapp.ui.theme.ToDoComposeAppTheme
import com.example.todocomposeapp.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private lateinit var navController: NavHostController
	private val sharedViewModel: SharedViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ToDoComposeAppTheme {
				navController = rememberNavController()
				SetupNavigation(navController = navController, sharedViewModel = sharedViewModel)
			}
		}
	}
}