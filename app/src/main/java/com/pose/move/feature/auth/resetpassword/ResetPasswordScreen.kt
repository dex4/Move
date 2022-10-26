package com.pose.move.feature.auth.resetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.widget.InputField
import com.pose.move.ui.widget.MaterialButton
import com.pose.move.ui.widget.inputfield.TrailIconBehavior

@Composable
fun ResetPasswordScreen(
    resetToken: String,
    onBackClick: () -> Unit,
    onPasswordResetSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_app), contentScale = ContentScale.Crop)
            .padding(vertical = 20.dp)
    ) {
        var newPassword by rememberSaveable { mutableStateOf("") }
        var confirmNPassword by rememberSaveable { mutableStateOf("") }
        val isButtonEnabled = rememberSaveable(newPassword, confirmNPassword) { newPassword.isNotEmpty() && confirmNPassword.isNotEmpty() }
        var isNewPasswordShown by rememberSaveable { mutableStateOf(false) }
        var isConfirmPasswordShown by rememberSaveable { mutableStateOf(false) }
        val passwordMismatchErrorMessage = stringResource(id = R.string.reset_password_not_matching_error_text)
        var errorMessage by rememberSaveable { mutableStateOf("") }

        IconButton(
            modifier = Modifier.padding(start = 10.dp),
            onClick = onBackClick
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Button"
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.reset_password_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 24.dp),
            text = "Token: $resetToken",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .padding(horizontal = 24.dp),
            value = newPassword,
            hint = stringResource(R.string.new_password_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = { newPassword = it },
            isPasswordVisible = isNewPasswordShown,
            trailIconBehavior = TrailIconBehavior.PasswordToggle(R.drawable.ic_show_input, R.drawable.ic_hide_input),
            onTrailIconClick = { isNewPasswordShown = !isNewPasswordShown }
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .padding(horizontal = 24.dp),
            value = confirmNPassword,
            hint = stringResource(R.string.confirm_password_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = {
                confirmNPassword = it
                errorMessage = ""
            },
            isPasswordVisible = isConfirmPasswordShown,
            trailIconBehavior = TrailIconBehavior.PasswordToggle(R.drawable.ic_show_input, R.drawable.ic_hide_input),
            onTrailIconClick = { isConfirmPasswordShown = !isConfirmPasswordShown },
            error = errorMessage
        )
        MaterialButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.reset_password_button_text),
            enabled = isButtonEnabled,
            onClick = {
                if (newPassword == confirmNPassword) {
                    onPasswordResetSuccess()
                } else {
                    errorMessage = passwordMismatchErrorMessage
                }
            }
        )
    }
}