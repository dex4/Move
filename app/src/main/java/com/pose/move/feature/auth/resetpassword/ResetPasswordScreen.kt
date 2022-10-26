package com.pose.move.feature.auth.resetpassword

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResetPasswordScreen(
    resetToken: String
) {
    Text(modifier = Modifier.fillMaxSize(), text = resetToken)
}