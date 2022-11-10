package com.pose.move.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
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
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    @DrawableRes
    endIcon: Int = ID_NULL
) {
    Button(
        modifier = modifier
            .height(56.dp),
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        border = if (!enabled) BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)) else null,
        colors = buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0f),
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            text = text,
            style = if (enabled) MaterialTheme.typography.labelLarge else MaterialTheme.typography.bodyLarge
        )

        if (endIcon != ID_NULL) {
            Icon(
                modifier = Modifier.padding(start = 12.dp),
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
        PrimaryButton("OK", Modifier.fillMaxWidth())
    }
}