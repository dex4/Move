package com.pose.move.feature.home.availablescooters.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import kotlinx.coroutines.launch

@Composable
fun SectionsHeader(
    modifier: Modifier = Modifier,
    headerItems: List<AvailableScootersListItem.Header>,
    sectionsListState: LazyListState = rememberLazyListState(),
    onItemClick: suspend (header: AvailableScootersListItem.Header) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
        state = sectionsListState
    ) {
        items(headerItems) { header ->
            Button(
                modifier = Modifier
                    .size(32.dp),
                shape = CircleShape,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                contentPadding = PaddingValues(0.dp),
                onClick = { coroutineScope.launch { onItemClick(header) } }
            ) {
                Text(
                    text = header.letter.toString(),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}