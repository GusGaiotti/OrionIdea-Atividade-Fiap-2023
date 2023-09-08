package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.orionidea.repository.InvestimentRepository
import br.com.fiap.orionidea.components.InvestmentList
import br.com.fiap.orionidea.components.InvestmentForm


@Composable
fun AddInvestmentScreen() {
    val context = LocalContext.current
    val investmentRepository = InvestimentRepository(context)

    var nameState by remember { mutableStateOf("") }
    var valueState by remember { mutableStateOf("") }
    var interestState by remember { mutableStateOf("") }
    var typeState by remember { mutableStateOf("") }
    var dailyLiqState by remember { mutableStateOf(false) }

    var listInvestmentsState by remember {
        mutableStateOf(investmentRepository.listInvestments())
    }

    Column {
        InvestmentForm(name = nameState,
            value = valueState,
            interest = interestState,
            type = typeState,
            dailyLiq = dailyLiqState,
            onNameChange = { nameState = it },
            onValueChange = { valueState = it },
            onInterestChange = { interestState = it },
            onTypeChange = { typeState = it },
            onAmigoChange = { dailyLiqState = it },
            atualizar = {
                listInvestmentsState = investmentRepository.listInvestments()
            })
        InvestmentList(listInvestmentsState) {
            listInvestmentsState = investmentRepository.listInvestments()
        }
    }
}

