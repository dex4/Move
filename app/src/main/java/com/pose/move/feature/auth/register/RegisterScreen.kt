package com.pose.move.feature.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.widget.InputField
import com.pose.move.ui.widget.MaterialButton
import com.pose.move.ui.widget.inputfield.TrailIconBehavior

@Composable
fun RegisterScreen(
    onRegisterButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_app), contentScale = Crop)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        var email by rememberSaveable { mutableStateOf("") }
        var userName by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val isButtonEnabled = rememberSaveable(email, userName, password) {
            email.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()
        }
        var isPasswordShown by rememberSaveable { mutableStateOf(false) }

        Image(painter = painterResource(R.drawable.ic_small_logo_white), contentDescription = "Authentication app logo")
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.register_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .alpha(0.6f),
            text = stringResource(R.string.register_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            value = email,
            hint = stringResource(R.string.authentication_email_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = Email),
            onValueChanged = { email = it },
            trailIconBehavior = TrailIconBehavior.Action(R.drawable.ic_clear),
            isTrailIconVisible = email.isNotEmpty(),
            onTrailIconClick = { email = "" }
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = userName,
            hint = stringResource(R.string.authentication_username_input_hint),
            onValueChanged = { userName = it },
            trailIconBehavior = TrailIconBehavior.Action(R.drawable.ic_clear),
            isTrailIconVisible = userName.isNotEmpty(),
            onTrailIconClick = { userName = "" }
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = password,
            hint = stringResource(R.string.authentication_password_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = { password = it },
            isPasswordVisible = isPasswordShown,
            trailIconBehavior = TrailIconBehavior.PasswordToggle(R.drawable.ic_show_input, R.drawable.ic_hide_input),
            onTrailIconClick = { isPasswordShown = !isPasswordShown }
        )
        MaterialButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = stringResource(R.string.register_start_button_text),
            enabled = isButtonEnabled,
            onClick = onRegisterButtonClick
        )
    }
}