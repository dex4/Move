package com.pose.move.feature.auth.forgotpassword

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.widget.inputfield.InputField
import com.pose.move.ui.widget.MaterialButton
import com.pose.move.ui.widget.inputfield.TrailIconBehavior

@Composable
fun ForgotPasswordScreen(
    loginInputEmail: String,
    onBackButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_app), contentScale = ContentScale.Crop)
            .padding(vertical = 20.dp)
    ) {
        var email by rememberSaveable { mutableStateOf(loginInputEmail) }
        val isButtonEnabled = rememberSaveable(email) { email.isNotEmpty() }

        IconButton(
            modifier = Modifier.padding(start = 10.dp),
            onClick = onBackButtonClick
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimary,
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Button"
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.forgot_password_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .alpha(0.6f),
            text = stringResource(R.string.forgot_password_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 24.dp),
            value = email,
            hint = stringResource(R.string.authentication_email_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            onValueChanged = { email = it },
            trailIconBehavior = TrailIconBehavior.Action(R.drawable.ic_clear),
            isTrailIconVisible = email.isNotEmpty(),
            onTrailIconClick = { email = "" }
        )
        MaterialButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp),
            text = stringResource(R.string.forgot_password_button_text),
            enabled = isButtonEnabled,
            onClick = {}
        )
    }
}