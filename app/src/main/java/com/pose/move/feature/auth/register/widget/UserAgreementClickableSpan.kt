package com.pose.move.feature.auth.register.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.util.Constants

@Composable
fun UserAgreementClickableSpan() {
    val termsAndConditionsText = stringResource(R.string.register_terms_and_conditions_label_text)
    val privacyPolicyText = stringResource(R.string.register_privacy_policy_label_text)
    val userAgreementText = stringResource(R.string.register_agreement_label_text_format, termsAndConditionsText, privacyPolicyText)
    val termsStartIndex = userAgreementText.indexOf(termsAndConditionsText)
    val privacyStartIndex = userAgreementText.indexOf(privacyPolicyText)
    val builder = AnnotatedString.Builder(userAgreementText)
    val spanStyle = SpanStyle(color = MaterialTheme.colorScheme.onPrimary, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.SemiBold)
    builder.addStringAnnotation(
        Constants.Register.TERMS_SPAN_TAG,
        Constants.Register.TERMS_URL,
        termsStartIndex,
        termsStartIndex + termsAndConditionsText.length
    )
    builder.addStyle(spanStyle, termsStartIndex, termsStartIndex + termsAndConditionsText.length)
    builder.addStringAnnotation(
        Constants.Register.POLICY_SPAN_TAG,
        Constants.Register.POLICY_URL,
        privacyStartIndex,
        privacyStartIndex + privacyPolicyText.length
    )
    builder.addStyle(spanStyle, privacyStartIndex, privacyStartIndex + privacyPolicyText.length)

    val annotatedString = builder.toAnnotatedString()
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = Modifier.padding(top = 24.dp),
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary),
        onClick = {
            val openUri = annotatedString.getStringAnnotations(Constants.Register.TERMS_SPAN_TAG, it, it).firstOrNull()?.item
                ?: annotatedString.getStringAnnotations(Constants.Register.POLICY_SPAN_TAG, it, it).firstOrNull()?.item
                ?: return@ClickableText

            uriHandler.openUri(openUri)
        }
    )
}