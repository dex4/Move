package com.pose.move.feature.auth.login.widget

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.pose.move.R
import com.pose.move.util.Constants

@Composable
fun RegisterClickableSpan(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
    val registerHereText = stringResource(R.string.login_new_account_label_text)
    val newAccountText = stringResource(R.string.login_new_account_label_text_format, registerHereText)
    val registerHereStartIndex = newAccountText.indexOf(registerHereText)
    val builder = AnnotatedString.Builder(newAccountText)
    val spanStyle = SpanStyle(color = MaterialTheme.colorScheme.onPrimary, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.SemiBold)
    builder.addStyle(spanStyle, registerHereStartIndex, registerHereStartIndex + registerHereText.length)
    builder.addStringAnnotation(
        Constants.Register.LOGIN_TAG,
        registerHereText,
        registerHereStartIndex,
        registerHereStartIndex + registerHereText.length
    )
    builder.addStyle(spanStyle, registerHereStartIndex, registerHereStartIndex + registerHereText.length)

    val annotatedString = builder.toAnnotatedString()
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary),
        onClick = { onRegisterClick() }
    )
}