package com.pose.move.ui.widget.announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pose.move.R

@Composable
fun Announcement(
    modifier: Modifier = Modifier,
    announcementData: AnnouncementData,
    onAlertDismiss: () -> Unit
 //TODO: Add action button
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(min = 80.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .clickable { onAlertDismiss() }
            .background(color = MaterialTheme.colorScheme.error, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_error),
            tint = MaterialTheme.colorScheme.onError,
            contentDescription = "Announcement icon"
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = announcementData.text,
            color = MaterialTheme.colorScheme.onError,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}