package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pose.move.ui.widget.MaterialButton

@Composable
fun AvailableScootersScreen(
    onClearStorageClick: (clearPhoto: Boolean, clearAppStorage: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AvailableScootersScreen")
        MaterialButton(
            modifier = Modifier.padding(top = 16.dp),
            text = "Logout",
            onClick = { onClearStorageClick(false, false) }
        )
        MaterialButton(
            modifier = Modifier.padding(top = 16.dp),
            text = "Logout & Clear Photo",
            onClick = { onClearStorageClick(true, false) }
        )
        MaterialButton(
            modifier = Modifier.padding(top = 16.dp),
            text = "Clear App",
            onClick = { onClearStorageClick(false, true) }
        )
    }
}