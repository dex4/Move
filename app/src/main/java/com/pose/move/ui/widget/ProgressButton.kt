package com.pose.move.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.ID_NULL

@Composable
fun ProgressButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit = {},
    @DrawableRes
    endIcon: Int = ID_NULL
) {
    Box(modifier = modifier) {
        PrimaryButton(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled && !loading,
            onClick = onClick,
            endIcon = endIcon,
        )
        if (loading) {
            CircularProgressIndicator(
                Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 4.dp
            )
        }
    }
}