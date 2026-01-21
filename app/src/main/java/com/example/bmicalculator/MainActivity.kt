package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.navigation.NavGraph
import com.example.bmicalculator.ui.theme.BmiCalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiCalculatorTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
