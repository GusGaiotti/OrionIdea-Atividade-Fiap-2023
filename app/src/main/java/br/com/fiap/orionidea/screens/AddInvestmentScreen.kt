package br.com.fiap.orionidea.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.orionidea.juros.JurosScreenViewModel
import br.com.fiap.orionidea.model.Investment
import br.com.fiap.orionidea.repository.InvestimentoRepository


@Composable
fun AddInvestmentScreen(
    jurosScreenViewModel: JurosScreenViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val investmentRepository = InvestimentoRepository(context)

    var nameState = remember {
        mutableStateOf("")
    }

    var valueState = remember {
        mutableStateOf("")
    }

    var diaryState = remember {
        mutableStateOf(false)
    }

    var listInvestmentsState = remember {
        mutableStateOf(investmentRepository.listarInvestimentos())
    }

    Column {
        InvestmentForm(
            name = nameState.value,
            telefone = valueState.value,
            amigo = diaryState.value,
            onNameChange = {
                nameState.value = it
            },
            onTelefoneChange = {
                valueState.value = it.toString()
            },
            onAmigoChange = {
                diaryState.value = it
            }
        ) {
            listInvestmentsState.value = investmentRepository.listarInvestimentos()
        }
        InvestmentList(listInvestmentsState.value){
            listInvestmentsState.value = investmentRepository.listarInvestimentos()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentForm(
    name: String,
    telefone: String,
    amigo: Boolean,
    onNameChange: (String) -> Unit,
    onTelefoneChange: (String) -> Unit,
    onAmigoChange: (Boolean) -> Unit,
    atualizar: () -> Unit
) {
    val context = LocalContext.current
    val investimentoRepository = InvestimentoRepository(context)
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastro de investimentos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(
                0xFF4B0082
            )
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
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telefone,
            onValueChange = { onTelefoneChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Valor do investimento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = amigo, onCheckedChange = {
                onAmigoChange(it)
            })
            Text(text = "Resgate diário")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val investment = Investment(
                    name = name,
                    valorAplicado = telefone,
                    liqDia = amigo
                )
                investimentoRepository.salvar(investment = investment)
                atualizar()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "CADASTAR",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun InvestmentList(investiments: List<Investment>, atualizar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (investiment in investiments) {
            InvestmentCard(investiment, context = LocalContext.current, atualizar)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun InvestmentCard(
    investment: Investment,
    context: Context,
    atualizar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = investment.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = investment.valorAplicado.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (investment.liqDia) "Amigo" else "Contato",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = {
                val investimentoRepository = InvestimentoRepository(context)
                investimentoRepository.excluir(investment)
                atualizar()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}