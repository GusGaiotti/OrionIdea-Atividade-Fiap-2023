package br.com.fiap.orionidea.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.model.Investment

@Composable
fun TypeCard(investimento: Investment, onTypeSelected: (String) -> Unit, isSelected: Boolean) {
    OutlinedButton(
        onClick = {
            val typeName = investimento.type
            onTypeSelected(typeName)
        },
        modifier = Modifier
            .size(120.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = investimento.type.toUpperCase(),
            fontSize = 14.sp,
            modifier = Modifier.padding(4.dp)
        )
    }

    Spacer(modifier = Modifier.width(8.dp)) // Adiciona um espaçamento horizontal entre os botões
}