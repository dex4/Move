package com.pose.move.feature.home.availablescooters.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pose.move.ui.theme.MoveTheme

@Composable
fun ScooterModelHeader(
    headerDetails: AvailableScootersListItem.Header
) {
    Text(
        modifier = Modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.primary)),
                CutCornerShape(topStart = 10.dp, bottomEnd = 10.dp)
            )
            .padding(start = 12.dp),
        text = headerDetails.letter.toString(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Preview
@Composable
fun ScooterModelHeaderPreview() {
    MoveTheme {
        ScooterModelHeader(AvailableScootersListItem.Header('A'))
    }
}