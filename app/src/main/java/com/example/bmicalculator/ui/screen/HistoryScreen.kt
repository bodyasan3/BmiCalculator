package com.example.bmicalculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bmicalculator.R
import com.example.bmicalculator.data.AppDatabase
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(navController: NavController) {
    val context = LocalContext.current
    val items by AppDatabase.getDatabase(context).bmiDao().getAll().observeAsState(emptyList())
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { navController.popBackStack() }) {
            Text(stringResource(R.string.back))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            scope.launch {
                AppDatabase.getDatabase(context).bmiDao().clearAll()
            }
        }) {
            Text(stringResource(R.string.clear_history))
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(items) { item ->
                Text(stringResource(R.string.history_item_format, item.weight, item.height, item.bmi))
            }
        }
    }
}
