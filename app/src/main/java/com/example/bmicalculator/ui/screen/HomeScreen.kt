package com.example.bmicalculator.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bmicalculator.R
import com.example.bmicalculator.data.AppDatabase
import com.example.bmicalculator.data.BmiEntity
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(text = stringResource(R.string.title), style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text(stringResource(R.string.weight)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text(stringResource(R.string.height)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val w = weight.toFloatOrNull()
                val h = height.toFloatOrNull()

                if (w != null && h != null && h > 0) {
                    val hInMeters = h / 100
                    val bmi = w / (hInMeters * hInMeters)
                    result = String.format("%.2f", bmi)

                    Toast.makeText(context, "BMI: $result", Toast.LENGTH_SHORT).show()

                    scope.launch {
                        AppDatabase.getDatabase(context)
                            .bmiDao()
                            .insert(BmiEntity(weight = w, height = h, bmi = bmi))
                    }
                } else {
                    Toast.makeText(context, context.getString(R.string.invalid_data), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.calculate))
        }

        Text(text = stringResource(R.string.result, result))

        Button(
            onClick = { navController.navigate("history") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.history))
        }
    }
}
