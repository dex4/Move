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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Announcement(
    modifier: Modifier = Modifier,
    announcementData: AnnouncementData,
    onAlertDismiss: () -> Unit,
    onActionPerformed: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(min = 80.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .clickable { onAlertDismiss() }
            .background(color = colorResource(announcementData.type.backgroundColor), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(announcementData.type.icon),
            tint = colorResource(announcementData.type.contentColor),
            contentDescription = "Announcement icon"
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = announcementData.text,
            color = colorResource(announcementData.type.contentColor),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        if (!announcementData.actionButtonText.isNullOrEmpty()) {
            TextButton(
                onClick = onActionPerformed
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = announcementData.actionButtonText,
                    color = colorResource(announcementData.type.actionColor),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}