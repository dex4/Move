package com.pose.move.feature.auth.register.widget

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
fun LoginClickableSpan(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
) {
    val loginHereText = stringResource(R.string.register_login_here_label_text)
    val alreadyRegisteredText = stringResource(R.string.register_existing_account_label_text_format, loginHereText)
    val loginHereStartIndex = alreadyRegisteredText.indexOf(loginHereText)
    val builder = AnnotatedString.Builder(alreadyRegisteredText)
    val spanStyle = SpanStyle(color = MaterialTheme.colorScheme.onPrimary, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.SemiBold)
    builder.addStyle(spanStyle, loginHereStartIndex, loginHereStartIndex + loginHereText.length)
    builder.addStringAnnotation(
        Constants.Register.LOGIN_TAG,
        loginHereText,
        loginHereStartIndex,
        loginHereStartIndex + loginHereText.length
    )
    builder.addStyle(spanStyle, loginHereStartIndex, loginHereStartIndex + loginHereText.length)

    val annotatedString = builder.toAnnotatedString()
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary),
        onClick = { onLoginClick() }
    )
}