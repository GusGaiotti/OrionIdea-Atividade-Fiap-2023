package br.com.fiap.orionidea.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.orionidea.R
import br.com.fiap.orionidea.juros.SimulateScreenViewModel
import br.com.fiap.orionidea.model.Investment
import br.com.fiap.orionidea.repository.InvestimentoRepository
import br.com.fiap.orionidea.components.InvestmentList

@Composable
fun AddInvestmentScreen(
    jurosScreenViewModel: SimulateScreenViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val investmentRepository = InvestimentoRepository(context)

    var nameState by remember { mutableStateOf("") }
    var valueState by remember { mutableStateOf("") }
    var interestState by remember { mutableStateOf("") }
    var typeState by remember { mutableStateOf("") }
    var dailyLiqState by remember { mutableStateOf(false) }

    var listInvestmentsState by remember {
        mutableStateOf(investmentRepository.listInvestments())
    }

    Column {
        InvestmentForm(
            name = nameState,
            value = valueState,
            interest = interestState,
            type = typeState,
            dailyLiq = dailyLiqState,
            onNameChange = { nameState = it },
            onValueChange = { valueState = it },
            onInterestChange = { interestState = it },
            onTypeChange = { typeState = it },
            onAmigoChange = { dailyLiqState = it }
        ) {
            listInvestmentsState = investmentRepository.listInvestments()
        }
        InvestmentList(listInvestmentsState) {
            listInvestmentsState = investmentRepository.listInvestments()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentForm(
    name: String,
    value: String,
    dailyLiq: Boolean,
    interest: String,
    type: String,
    onNameChange: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onInterestChange: (String) -> Unit,
    onTypeChange: (String) -> Unit,
    onAmigoChange: (Boolean) -> Unit,
    atualizar: () -> Unit
) {
    val context = LocalContext.current
    val investimentoRepository = InvestimentoRepository(context)

    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastro de investimentos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4B0082)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome do investimento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Valor do investimento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = type,
            onValueChange = { onTypeChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Tipo do investimento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = interest,
            onValueChange = { onInterestChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Taxa de juros")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = dailyLiq, onCheckedChange = { onAmigoChange(it) })
            Text(text = "Resgate diário")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                showError = false

                try {
                    val valueDouble = value.replace(",", ".").toDouble()
                    val interestDouble = interest.replace(",", ".").toDouble()

                    if (name.matches(Regex(".*\\d.*")) || type.matches(Regex(".*\\d.*"))) {
                        showError = true
                        return@OutlinedButton
                    }

                    val investment = Investment(
                        name = name,
                        valueApplied = valueDouble,
                        dailyLiq = dailyLiq,
                        type = type,
                        interest = interestDouble
                    )
                    investimentoRepository.save(investment = investment)
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
                text = "CADASTRAR",
                fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                color = Color(0xFFFFA500),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Revise os campos inválidos",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}