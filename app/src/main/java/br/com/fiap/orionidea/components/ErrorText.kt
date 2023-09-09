package br.com.fiap.orionidea.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorText(showError: Boolean, errorMessage: String) {
    if (showError) {
        Text(
            text = errorMessage,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}