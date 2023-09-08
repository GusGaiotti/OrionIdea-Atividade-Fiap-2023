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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.R
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
        modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.interest_calculation),
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
                        text = stringResource(id = R.string.investment_data),
                        fontWeight = FontWeight.Bold
                    )
                    CaixaDeEntrada(
                        value = value,
                        placeholder = stringResource(id = R.string.how_much_invest),
                        label = stringResource(id = R.string.value_invest),
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onValueChanged(it)
                    }
                    CaixaDeEntrada(
                        value = rate,
                        placeholder = stringResource(id = R.string.monthly_interest),
                        label = stringResource(id = R.string.monthly_interest_real),
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onRateChanged(it)
                    }
                    CaixaDeEntrada(
                        value = period,
                        placeholder = stringResource(id = R.string.investment_period),
                        label = stringResource(id = R.string.investment_period_real),
                        modifier = Modifier,
                        keyboardType = KeyboardType.Decimal
                    ) {
                        jurosScreenViewModel.onPeriodChanged(it)
                    }
                    Button(
                        onClick = {
                            jurosScreenViewModel.calculateInvestmentInterest()
                            jurosScreenViewModel.calculateAmountInvestment()
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    ) {
                        Text(text = stringResource(id = R.string.calculate))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CardResultado(juros = interest, montante = amount)
        }
    }
}



