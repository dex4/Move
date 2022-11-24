package com.pose.move.feature.home.availablescooters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.theme.MoveTheme

@Composable
fun ScooterListItem(
    scooterDetails: AvailableScootersListItem.Scooter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .shadow(2.dp, MaterialTheme.shapes.medium)
            .height(120.dp)
            .fillMaxWidth()
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
        ScooterListItem(
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