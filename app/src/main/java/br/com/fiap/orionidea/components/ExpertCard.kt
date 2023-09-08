package br.com.fiap.orionidea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.model.Expert

@Composable
fun ExpertCard(expert: Expert) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(2f)
            ) {
                Text(
                    text = expert.name, fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = expert.city, fontSize = 14.sp, fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = "+55 " + expert.telephone.toString(),
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCC4A0D),
            )
        }
    }
}

