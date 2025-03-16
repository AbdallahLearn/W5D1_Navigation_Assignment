package com.example.w5d1_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.w5d1_navigation.ui.theme.W5D1_NavigationTheme

sealed class Screen(val route: String, val title: String, val showBackButton: Boolean) {
    object FirstActivity : Screen("MainActivity", "First Screen", false)
    object SecondActivity : Screen("SecondActivity", "Second Screen", true)
    object ThirdActivity : Screen("ThirdActivity", "Third Screen", true)
}


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var currentTitle by remember { mutableStateOf(Screen.FirstActivity.title) }
            var showBackButton by remember { mutableStateOf(Screen.FirstActivity.showBackButton) }

            W5D1_NavigationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            title = { Text(currentTitle) },
                            navigationIcon = {
                                if (showBackButton) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.FirstActivity.route
                        ) {
                            composable(Screen.FirstActivity.route) {
                                FirstScreen(navController) {
                                    currentTitle = Screen.FirstActivity.title
                                    showBackButton = Screen.FirstActivity.showBackButton
                                }
                            }
                            composable(Screen.SecondActivity.route) {
                                SecondScreen(navController) {
                                    currentTitle = Screen.SecondActivity.title
                                    showBackButton = Screen.SecondActivity.showBackButton
                                }
                            }
                            composable(Screen.ThirdActivity.route) {
                                ThirdScreen(navController) {
                                    currentTitle = Screen.ThirdActivity.title
                                    showBackButton = Screen.ThirdActivity.showBackButton
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController, onTitleUpdate: () -> Unit) {
    LaunchedEffect(Unit) {
        onTitleUpdate() // Update the title and back button state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the First Screen")
        Button(onClick = {
            navController.navigate(Screen.SecondActivity.route)
        }) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController, onTitleUpdate: () -> Unit) {
    LaunchedEffect(Unit) {
        onTitleUpdate() // Update the title and back button state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Second Screen")
        Button(onClick = {
            navController.navigate(Screen.ThirdActivity.route)
        }) {
            Text("Go to Third Screen")
        }
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController, onTitleUpdate: () -> Unit) {
    LaunchedEffect(Unit) {
        onTitleUpdate() // Update the title and back button state
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Third Screen")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
    }
}