package br.com.fiap.orionidea.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.R
import br.com.fiap.orionidea.model.Expert
import br.com.fiap.orionidea.repository.ExpertRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpertForm(
    name: String,
    city: String,
    telephone: String,
    onNameChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onTelephoneChange: (String) -> Unit,
    atualizar: () -> Unit
) {
    val context = LocalContext.current
    val expertRepository = ExpertRepository(context)

    var showError by remember { mutableStateOf(false) }
    var showSucces by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.want_to_be_expert_lc),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4B0082)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name,
            onValueChange = { onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Digite seu nome completo")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = city,
            onValueChange = { onCityChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.what_is_your_city))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telephone,
            onValueChange = { onTelephoneChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = R.string.what_is_your_contact))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                showError = false

                if (name.isEmpty() || city.isEmpty() || telephone.isEmpty()) {
                    showError = true
                    return@OutlinedButton
                }

                try {
                    if (name.matches(Regex(".*\\d.*")) || city.matches(Regex(".*\\d.*"))) {
                        showError = true
                        return@OutlinedButton
                    }

                    val expert = Expert(
                        name = name, city = city, telephone = telephone
                    )
                    expertRepository.save(expert = expert)
                    showSucces = true
                    atualizar()
                } catch (e: Exception) {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Black),
        ) {
            Text(
                text = stringResource(id = R.string.submit),
                fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                color = Color(0xFFCC4A0D),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.invalid),
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        if (showSucces) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Solicitação enviada!",
                color = Color(0xFF349B0F),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
