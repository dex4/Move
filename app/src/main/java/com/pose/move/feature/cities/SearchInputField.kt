package com.pose.move.feature.cities

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pose.move.R
import com.pose.move.ui.widget.inputfield.InputField
import com.pose.move.ui.widget.inputfield.TrailIconBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    onClearSearchQuery: () -> Unit,
    onValueChanged: (searchQuery: String) -> Unit
) {
    InputField(
        modifier = modifier,
        value = text,
        hint = hint,
        leadingIconRes = R.drawable.ic_search,
        onValueChanged = onValueChanged,
        textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.outline,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.tertiary,
            unfocusedLabelColor = MaterialTheme.colorScheme.tertiary
        ),
        trailIconBehavior = TrailIconBehavior.Action(R.drawable.ic_clear),
        onTrailIconClick = onClearSearchQuery,
        isTrailIconVisible = text.isNotEmpty()
    )
}