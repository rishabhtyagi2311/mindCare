package com.example.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun DisplayHistory()
{
    val repository = remember { HistoryRepo() }
    var bmiRecords by remember { mutableStateOf<List<BmiElement>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        repository.getUserBmiEntries(unique){ entries ->

            bmiRecords = entries ?: emptyList()
            isLoading = false
        }
    }

    Box(modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            Text(text = "Loading...")
        } else {
            if (bmiRecords.isEmpty()) {
                Text(text = "No records found")
            } else {
                LazyColumn {
                    items(bmiRecords) { record ->
                        BmiRecordCard(bmiRecord = record)
                    }
                }
            }
        }
    }

}


@Composable
fun BmiRecordCard(bmiRecord: BmiElement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Date: ${bmiRecord.date}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Height: ${bmiRecord.height} cm")
            Text("Weight: ${bmiRecord.weight} kg")
            Text("BMI: ${bmiRecord.bmi}")
            Text("Message: ${bmiRecord.message}")
        }
    }
}

