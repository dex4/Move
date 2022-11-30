package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem.Companion.getAlphabeticalIndex
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun AvailableScootersScreen(
    itemsList: List<AvailableScootersListItem>,
    onSwipeScooterItem: (scooterId: Int, newRevealState: Boolean) -> Unit,
    onReportIssue: (scooterId: Int) -> Unit,
    onMenuButtonClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        val sectionsListState = rememberLazyListState()
        val itemsListState = rememberLazyListState()
        val headerItems = itemsList.filterIsInstance<AvailableScootersListItem.Header>()
        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = onMenuButtonClick,
            modifier = Modifier
                .padding(12.dp)
                .size(48.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = "Menu button"
            )
        }

        SectionsHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            headerItems = headerItems,
            sectionsListState = sectionsListState
        ) { header ->
            coroutineScope.launch {
                itemsListState.animateScrollToItem(
                    itemsList.indexOfFirst { it is AvailableScootersListItem.Header && it.letter == header.letter }
                )
            }
        }

        ScooterItemsList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            itemsList = itemsList,
            itemsListState = itemsListState,
            onSwipeScooterItemComplete = onSwipeScooterItem,
            onReportIssue = onReportIssue
        )

        LaunchedEffect(itemsListState) {
            snapshotFlow { itemsListState.firstVisibleItemIndex }
                .map { index -> itemsList[index].getAlphabeticalIndex() }
                .distinctUntilChanged()
                .collect {
                    sectionsListState.animateScrollToItem(it)
                }
        }
    }
}