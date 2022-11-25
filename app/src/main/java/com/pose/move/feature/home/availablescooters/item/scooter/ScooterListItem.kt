package com.pose.move.feature.home.availablescooters.item.scooter

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.feature.home.availablescooters.item.AvailableScootersListItem
import com.pose.move.ui.theme.MoveTheme
import java.lang.Integer.max
import java.lang.Integer.min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SwipeableScooterItemContainer(
    scooterDetails: AvailableScootersListItem.Scooter,
    modifier: Modifier = Modifier
) {
    var isRevealed by remember { mutableStateOf(false) }
//    val transitionState = remember {
//        MutableTransitionState(isRevealed).apply { targetState = !isRevealed }
//    }
//    val transition = updateTransition(targetState = transitionState, label = "swipeTransition")
    var offset by remember { mutableStateOf(0) }
//    val offsetTransition by transition.animateFloat(
//        label = "offsetTransition",
//        transitionSpec = { tween(150) },
//        targetValueByState = { if (!isRevealed) 0f else -(300f + offset) }
//    )
//    LaunchedEffect(key1 = isRevealed) {
//        if (offset in (-249 .. -1)) {
//            launch {
//                val endValue = if (isRevealed) -250 else 0
//                while (offset != endValue) {
//                    offset = if (isRevealed) {
//                        max(-250, offset - 10)
//                    } else {
//                        min(0, offset + 10)
//                    }
//                    delay(10L)
//                }
//            }
//        }
//    }
    Box(
        modifier = modifier
            .shadow(2.dp, MaterialTheme.shapes.medium)
            .height(120.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
    ) {
        ScooterActions({ Log.d("WRKR", "Report for scooter ${scooterDetails.id}") })
        ScooterListItem(
            modifier = Modifier
                .offset { IntOffset(offset, 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            Log.d("WRKR", offset.toString())
//                            when (offset) {
//                                in (-250 .. -125) -> isRevealed = true
//                                in (-124 .. 0) -> isRevealed = false
//                            }
                        }
                    ) { _, dragAmount ->
                        when {
                            dragAmount < 0 -> offset = max(-250, offset + dragAmount.roundToInt())
                            dragAmount > 0 -> offset = min(0, offset + dragAmount.roundToInt())
                        }
//                        when (offset) {
//                            -250 -> isRevealed = true
//                            0 -> isRevealed = false
//                        }
                    }
                },
            scooterDetails = scooterDetails
        )
    }
}

@Composable
fun ScooterListItem(
    scooterDetails: AvailableScootersListItem.Scooter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.bg_scooter_item_highlight),
                contentDescription = "Scooter image highlight"
            )
            Image(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .size(100.dp),
                painter = painterResource(R.drawable.ic_scooter),
                contentDescription = "Scooter image"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.scooters_list_item_model_label_format, scooterDetails.modelName),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_location),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Location pin"
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = scooterDetails.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(getBatteryIconForCharge(scooterDetails.battery)),
                    contentDescription = "Battery percentage remaining icon"
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = stringResource(R.string.scooters_list_item_battery_label_format, scooterDetails.battery),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = scooterDetails.pin,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 24.dp)
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
                    painter = painterResource(R.drawable.ic_open),
                    contentDescription = "Open in maps icon"
                )
            }
        }
    }
}

private fun getBatteryIconForCharge(batteryChargeLeft: Int): Int =
    when (batteryChargeLeft) {
        in (100 downTo 89) -> R.drawable.ic_battery_100
        in (88 downTo 69) -> R.drawable.ic_battery_80
        in (68 downTo 49) -> R.drawable.ic_battery_60
        in (48 downTo 1) -> R.drawable.ic_battery_40
        else -> R.drawable.ic_battery_0
    }

@Preview
@Composable
private fun ScooterListItemPreview() {
    MoveTheme {
        SwipeableScooterItemContainer(
            AvailableScootersListItem.Scooter(
                0,
                "A",
                "Str. Alverna, nr. 17",
                65,
                "#AB00"
            )
        )
    }
}