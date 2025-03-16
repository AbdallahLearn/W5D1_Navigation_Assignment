package com.example.w5d1_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.w5d1_navigation.ui.theme.W5D1_NavigationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            W5D1_NavigationTheme {
                SecondActivityScreen()
            }
        }
    }
}

@Composable
fun SecondActivityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Second Activity")
        Button(onClick = {
            // Finish the activity to go back to MainActivity
            // (This requires access to the activity context)
        }) {
            Text("Go Back")
        }
    }
}