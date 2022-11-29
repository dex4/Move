package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import com.pose.move.feature.home.availablescooters.item.ScooterModelHeader
import com.pose.move.feature.home.availablescooters.item.scooter.SwipeableScooterItemContainer

@Composable
fun ScooterItemsList(
    modifier: Modifier = Modifier,
    itemsList: List<AvailableScootersListItem>,
    itemsListState: LazyListState,
    onSwipeScooterItemComplete: (scooterId: Int, newRevealState: Boolean) -> Unit,
    onReportIssue: (scooterId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
        state = itemsListState,
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(itemsList) { item ->
            when (item) {
                is AvailableScootersListItem.Header -> ScooterModelHeader(item)
                is AvailableScootersListItem.Scooter -> SwipeableScooterItemContainer(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    scooterDetails = item,
                    onSwipeComplete = onSwipeScooterItemComplete,
                    onReportIssueClick = onReportIssue
                )
            }
        }
    }
}