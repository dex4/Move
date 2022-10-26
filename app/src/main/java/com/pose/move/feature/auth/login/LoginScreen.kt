package com.pose.move.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.feature.auth.login.widget.RegisterClickableSpan
import com.pose.move.ui.widget.InputField
import com.pose.move.ui.widget.MaterialButton
import com.pose.move.ui.widget.inputfield.TrailIconBehavior

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onCreateNewAccountClick: () -> Unit,
    onForgotPasswordClick: (email: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_app), contentScale = ContentScale.Crop)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val isButtonEnabled = rememberSaveable(email, password) {
            email.isNotEmpty() && password.isNotEmpty()
        }
        var isPasswordShown by rememberSaveable { mutableStateOf(false) }

        Image(painter = painterResource(R.drawable.ic_small_logo_white), contentDescription = "Authentication app logo")
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .alpha(0.6f),
            text = stringResource(R.string.login_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            value = email,
            hint = stringResource(R.string.authentication_email_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            onValueChanged = { email = it },
            trailIconBehavior = TrailIconBehavior.Action(R.drawable.ic_clear),
            isTrailIconVisible = email.isNotEmpty(),
            onTrailIconClick = { email = "" }
        )
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = password,
            hint = stringResource(R.string.authentication_password_input_hint),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = { password = it },
            isPasswordVisible = isPasswordShown,
            trailIconBehavior = TrailIconBehavior.PasswordToggle(R.drawable.ic_show_input, R.drawable.ic_hide_input),
            onTrailIconClick = { isPasswordShown = !isPasswordShown }
        )
        ClickableText(
            modifier = Modifier.padding(top = 32.dp),
            text = AnnotatedString(
                stringResource(id = R.string.login_forgot_password_label_text),
                SpanStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    textDecoration = Underline,
                    fontWeight = SemiBold,
                )
            ),
            onClick = { onForgotPasswordClick(email) }
        )
        MaterialButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = stringResource(R.string.login_button_text),
            enabled = isButtonEnabled,
            onClick = onLoginSuccess
        )
        RegisterClickableSpan(Modifier.padding(top = 32.dp), onCreateNewAccountClick)
    }
}