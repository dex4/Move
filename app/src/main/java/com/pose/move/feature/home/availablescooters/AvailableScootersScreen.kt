package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem.Companion.getAlphabeticalIndex
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun AvailableScootersScreen(
    itemsList: List<AvailableScootersListItem>,
    onSwipeScooterItem: (scooterId: Int, newRevealState: Boolean) -> Unit,
    onReportIssue: (scooterId: Int) -> Unit,
    onMenuButtonClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        val sectionsListState = rememberLazyListState()
        val itemsListState = rememberLazyListState()
        val headerItems = itemsList.filterIsInstance<AvailableScootersListItem.Header>()

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .size(48.dp),
            shape = CircleShape,
            shadowElevation = 4.dp
        ) {
            IconButton(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, CircleShape),
                onClick = onMenuButtonClick
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onBackground,
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Account Button"
                )
            }
        }

        SectionsHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp),
            headerItems = headerItems,
            sectionsListState = sectionsListState
        ) { header ->
            itemsListState.animateScrollToItem(
                itemsList.indexOfFirst { it is AvailableScootersListItem.Header && it.letter == header.letter }
            )
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