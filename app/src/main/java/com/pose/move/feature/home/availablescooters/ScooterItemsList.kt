package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScooterItemsList(
    modifier: Modifier = Modifier,
    itemsList: List<AvailableScootersListItem>,
    itemsListState: LazyListState
) {
    LazyColumn(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
        state = itemsListState,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(itemsList) { item ->
            when (item) {
                is AvailableScootersListItem.Header -> Text(
                    modifier = Modifier.height(48.dp),
                    text = item.letter.toString(),
                    style = MaterialTheme.typography.titleSmall,
                )
                is AvailableScootersListItem.Scooter -> ScooterListItem(scooterDetails = item)
            }
        }
    }
}