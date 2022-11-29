package com.pose.move.feature.home.availablescooters.item.scooter

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import com.pose.move.feature.home.availablescooters.item.scooter.SwipeState.CLOSED
import com.pose.move.feature.home.availablescooters.item.scooter.SwipeState.OPEN
import com.pose.move.feature.home.availablescooters.item.scooter.SwipeState.TRANSITIONING
import com.pose.move.ui.theme.MoveTheme
import java.lang.Integer.max
import java.lang.Integer.min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SwipeableScooterItemContainer(
    scooterDetails: AvailableScootersListItem.Scooter,
    modifier: Modifier = Modifier,
    onReportIssueClick: (scooterId: Int) -> Unit,
    onSwipeComplete: (scooterId: Int, newRevealState: Boolean) -> Unit
) {
    var offset by remember { mutableStateOf(if (scooterDetails.isRevealed) MAX_SWIPE_OFFSET else MIN_SWIPE_OFFSET) }
    var swipeState by remember { mutableStateOf(if (scooterDetails.isRevealed) OPEN else CLOSED) }

    Box(
        modifier = modifier
            .shadow(2.dp, MaterialTheme.shapes.medium)
            .height(120.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
    ) {
        ScooterActions({ onReportIssueClick(scooterDetails.id) })
        ScooterListItem(
            modifier = Modifier
                .offset { IntOffset(offset, 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            when (offset) {
                                in (MAX_SWIPE_OFFSET..MAX_SWIPE_OFFSET / 2) -> {
                                    swipeState = OPEN
                                    if (!scooterDetails.isRevealed) {
                                        onSwipeComplete(scooterDetails.id, true)
                                    }
                                }
                                in (MAX_SWIPE_OFFSET / 2 + 1..MIN_SWIPE_OFFSET) -> {
                                    swipeState = CLOSED
                                    if (scooterDetails.isRevealed) {
                                        onSwipeComplete(scooterDetails.id, false)
                                    }
                                }
                            }
                        }
                    ) { _, dragAmount ->
                        swipeState = TRANSITIONING
                        when {
                            dragAmount < 0 -> offset = max(MAX_SWIPE_OFFSET, offset + dragAmount.roundToInt())
                            dragAmount > 0 -> offset = min(MIN_SWIPE_OFFSET, offset + dragAmount.roundToInt())
                        }
                    }
                },
            scooterDetails = scooterDetails
        )
    }

    LaunchedEffect(scooterDetails, swipeState) {
        if (swipeState == TRANSITIONING) return@LaunchedEffect
        launch {
            val currentSwipeState = swipeState == OPEN && scooterDetails.isRevealed
            val endValue = if (currentSwipeState) MAX_SWIPE_OFFSET else MIN_SWIPE_OFFSET
            while (offset != endValue) {
                offset = if (currentSwipeState) {
                    max(MAX_SWIPE_OFFSET, offset - ANIMATED_OFFSET_UPDATE_STEP)
                } else {
                    min(MIN_SWIPE_OFFSET, offset + ANIMATED_OFFSET_UPDATE_STEP)
                }
                delay(ANIMATED_OFFSET_UPDATE_DELAY)
            }
        }
    }
}

private const val MAX_SWIPE_OFFSET = -300
private const val MIN_SWIPE_OFFSET = 0
private const val ANIMATED_OFFSET_UPDATE_DELAY = 10L
private const val ANIMATED_OFFSET_UPDATE_STEP = 15

@Preview
@Composable
private fun SwipeableScooterItemContainerPreview() {
    MoveTheme {
        SwipeableScooterItemContainer(
            AvailableScootersListItem.Scooter(
                0,
                "A",
                "Str. Alverna, nr. 17",
                65,
                "#AB00"
            ),
            onReportIssueClick = {},
            onSwipeComplete = { _, _ -> }
        )
    }
}