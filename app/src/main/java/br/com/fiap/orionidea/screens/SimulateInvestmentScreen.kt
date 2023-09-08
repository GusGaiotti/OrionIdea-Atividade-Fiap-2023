package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.components.CaixaDeEntrada
import br.com.fiap.orionidea.components.CardResultado
import br.com.fiap.orionidea.juros.SimulateScreenViewModel

@Composable
fun SimulateInvestmentScreen(
    jurosScreenViewModel: SimulateScreenViewModel
) {
    val value by jurosScreenViewModel.valueState.observeAsState(initial = "")
    val rate by jurosScreenViewModel.rateState.observeAsState(initial = "")
    val period by jurosScreenViewModel.periodState.observeAsState(initial = "")
    val interest by jurosScreenViewModel.interestState.observeAsState(initial = 0.0)
    val amount by jurosScreenViewModel.amountState.observeAsState(initial = 0.0)

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Cálculo de Juros Simples",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Dados do Investimento",
                        fontWeight = FontWeight.Bold
                    )
                    CaixaDeEntrada(
                        value = value,
                        placeholder = "Quanto deseja investir",
                        label = "Valor do investimento",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onValueChanged(it)
                    }
                    CaixaDeEntrada(
                        value = rate,
                        placeholder = "Qual a taxa de juros mensal?",
                        label = "Taxa de juros mensal",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onRateChanged(it)
                    }
                    CaixaDeEntrada(
                        value = period,
                        placeholder = "Qual o período do investimento em meses?",
                        label = "Período em meses",
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onPeriodChanged(it)
                    }
                    Button(
                        onClick = {
                            jurosScreenViewModel.calculateInvestmentInterest()
                            jurosScreenViewModel.calculateAmountInvestment()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    ) {
                        Text(text = "CALCULAR")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CardResultado(juros = interest, montante = amount)
        }
    }
}



