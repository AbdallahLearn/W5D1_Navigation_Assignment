package com.example.w5d1_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.w5d1_navigation.ui.theme.W5D1_NavigationTheme

sealed class Screen(val route: String, val title: String, val showBackButton: Boolean) {
    object FirstActivity : Screen("MainActivity", "First Screen", false)
    object SecondActivity : Screen("SecondActivity/{name}", "Second Screen", true)
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
                    },
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                // First Screen Button
                                Button(
                                    modifier = Modifier.width(100.dp), // Set a fixed width for the button
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Gray,
                                    ),
                                    onClick = {
                                        navController.navigate(Screen.FirstActivity.route)
                                    }
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally // Align icon and text vertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "Home",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp)) // Add spacing between icon and text
                                        Text(
                                            text = "1rt",
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                                // Second Screen Button
                                Button(
                                    modifier = Modifier.width(100.dp), // Set a fixed width for the button
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Gray,
                                    ),
                                    onClick = {
                                        navController.navigate(Screen.SecondActivity.route)
                                    }
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally // Align icon and text vertically
                                    )  {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "Profile",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp)) // Add spacing between icon and text
                                        Text(
                                            text = "2nd",
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                                // Third Screen Button
                                Button(
                                    modifier = Modifier.width(100.dp), // Set a fixed width for the button
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Gray,
                                    ),
                                    onClick = {
                                        navController.navigate(Screen.ThirdActivity.route)
                                    }
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally // Align icon and text vertically
                                    )  {
                                        Icon(
                                            imageVector = Icons.Default.Settings,
                                            contentDescription = "Settings",
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp)) // Add spacing between icon and text
                                        Text(
                                            text = "3rd",
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
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
                            composable(
                                    route = Screen.SecondActivity.route,
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType } // Define the argument
                            )
                            ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name") // Retrieve the argument
                            SecondScreen(
                                navController = navController,
                                onTitleUpdate = {
                                    currentTitle = Screen.SecondActivity.title
                                    showBackButton = Screen.SecondActivity.showBackButton
                                },
                                name = name // Pass the name to SecondScreen
                            )
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
    var text by remember {
        mutableStateOf("")
    }
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
        TextField(
            value= text, onValueChange = {text = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Enter you name "
                )
            }
        )
        Button(onClick = {
            navController.navigate("SecondActivity/${text}")
        }){
            Text(
                "send text"
            )

        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("This is the First Screen")
        Button(onClick = {
            navController.navigate(Screen.SecondActivity.route)
        }) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen( navController: NavController,
                  onTitleUpdate: () -> Unit,
                  name: String?) {
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
        Box(){
            Text(
                text = "Hello, ${name?: "User"}"
            )
        }

        Spacer(modifier= Modifier.height(10.dp))
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