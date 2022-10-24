package com.pose.move.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.ID_NULL
import com.pose.move.ui.theme.MoveTheme

@Composable
fun MaterialButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    @DrawableRes
    endIcon: Int = ID_NULL
) {
    Button(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.large)
            .height(56.dp),
        onClick = { onClick.invoke() },
        enabled = isEnabled,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        if (endIcon != ID_NULL) {
            Icon(
                painter = painterResource(endIcon),
                contentDescription = "Button Icon"
            )
        }
    }
}

@Preview
@Composable
fun MaterialButtonPreview() {
    MoveTheme {
        MaterialButton("OK", Modifier.fillMaxWidth())
    }
}