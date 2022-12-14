package com.pose.move.ui.widget.inputfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import com.pose.move.ui.theme.MoveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String = "",
    hint: String = "",
    error: String? = null,
    supportingText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isTrailIconVisible: Boolean = true,
    trailIconBehavior: TrailIconBehavior = TrailIconBehavior.None,
    onTrailIconClick: (() -> Unit)? = null,
    onValueChanged: (String) -> Unit,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = TextFieldDefaults.filledShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            selectionColors = TextSelectionColors(
                MaterialTheme.colorScheme.onPrimary,
                LocalTextSelectionColors.current.backgroundColor
            )
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = when {
            trailIconBehavior is TrailIconBehavior.PasswordToggle && !isPasswordVisible -> PasswordVisualTransformation()
            trailIconBehavior is TrailIconBehavior.PasswordToggle && isPasswordVisible -> VisualTransformation.Companion.None
            else -> visualTransformation
        },
        label = { InputFieldLabel(hint) },
        trailingIcon = {
            InputFieldTrailingIcon(
                trailIconBehavior = trailIconBehavior,
                isTrailIconVisible = isTrailIconVisible,
                isPasswordVisible = isPasswordVisible,
                onTrailIconClick = {
                    onTrailIconClick?.invoke()
                    if (trailIconBehavior is TrailIconBehavior.PasswordToggle) isPasswordVisible = !isPasswordVisible
                })
        },
        supportingText = { InputFieldSupportingText(error, supportingText) },
        isError = !error.isNullOrEmpty()
    )
}

@Composable
private fun InputFieldLabel(labelText: String) {
    Text(
        text = labelText,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = Ellipsis
    )
}

@Composable
private fun InputFieldSupportingText(error: String? = null, supportingText: String? = null) {
    val displayedValue = error ?: supportingText ?: return
    val supportingTextColor = if (!error.isNullOrEmpty()) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Text(
        text = displayedValue,
        style = MaterialTheme.typography.bodySmall,
        color = supportingTextColor,
        maxLines = 1,
        overflow = Ellipsis
    )
}

@Composable
private fun InputFieldTrailingIcon(
    trailIconBehavior: TrailIconBehavior,
    onTrailIconClick: (() -> Unit)?,
    isTrailIconVisible: Boolean,
    isPasswordVisible: Boolean
) {
    if (trailIconBehavior == TrailIconBehavior.None ||
        !isTrailIconVisible && trailIconBehavior is TrailIconBehavior.Action
    ) return

    val iconRes = when {
        trailIconBehavior is TrailIconBehavior.Action -> trailIconBehavior.actionIcon
        trailIconBehavior is TrailIconBehavior.PasswordToggle && isPasswordVisible -> trailIconBehavior.passwordHiddenIcon
        trailIconBehavior is TrailIconBehavior.PasswordToggle && !isPasswordVisible -> trailIconBehavior.passwordShownIcon
        else -> return
    }
    IconButton(
        onClick = { onTrailIconClick?.invoke() },
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            painter = painterResource(iconRes),
            contentDescription = "Input Trail Icon"
        )
    }
}

@Preview
@Composable
fun InputFieldPreview() {
    MoveTheme {
        InputField(value = "email@tapp.com", hint = "Email") {}
    }
}