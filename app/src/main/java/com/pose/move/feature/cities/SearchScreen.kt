package com.pose.move.feature.cities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pose.move.R

@Composable
fun SearchScreen(
    onCancelButtonClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChanged = { searchQuery = it },
            onClearSearchQuery = { searchQuery = "" },
            onCancelButtonClick = onCancelButtonClick
        )
    }
}

@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (searchQuery: String) -> Unit,
    onClearSearchQuery: () -> Unit,
    onCancelButtonClick: () -> Unit
) {
    Row {
        SearchInputField(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            hint = "Find city or scooters model locations",
            text = searchQuery,
            onValueChanged = { onSearchQueryChanged(searchQuery) },
            onClearSearchQuery = { onClearSearchQuery() }
        )

        TextButton(
            onClick = onCancelButtonClick,
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(stringResource(R.string.search_cancel_button_text))
        }
    }
}