package br.com.fiap.orionidea.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.orionidea.model.Investment
import br.com.fiap.orionidea.repository.InvestimentoRepository




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindInvestmentScreen(navController: NavHostController) {

    val context = LocalContext.current
    val investimentoRepository = InvestimentoRepository(context)

    var searchTextState by remember {
        mutableStateOf("")
    }

    var allInvestments by remember {
        mutableStateOf(investimentoRepository.listarInvestimentos())
    }

    var filteredInvestments by remember {
        mutableStateOf(allInvestments)
    }

    var selectedType by remember {
        mutableStateOf<String?>(null)
    }

            Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Meus investimentos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchTextState,
                    onValueChange = {
                        searchTextState = it
                        // Filtra a lista completa de investimentos com base no texto de pesquisa
                        filteredInvestments = allInvestments.filter { investment ->
                            investment.nome.contains(searchTextState, ignoreCase = true)
                        }
                    },
                    modifier = Modifier
                        .weight(1f),
                    label = {
                        Text(text = "Buscar Investimento")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            // Filtra a lista completa de investimentos com base no texto de pesquisa
                            filteredInvestments = allInvestments.filter { investment ->
                                investment.nome.contains(searchTextState, ignoreCase = true)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                if (selectedType != null) {
                    OutlinedButton(
                        onClick = {
                            // Redefine o estado para a lista completa
                            selectedType = null
                            filteredInvestments = allInvestments
                        },
                        modifier = Modifier
                            .heightIn(min = 48.dp), shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "LIMPAR FILTRO", fontSize = 11.sp,)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(modifier = Modifier.padding(16.dp)) {
                items(filteredInvestments.distinctBy { it.tipo }) { investimento ->
                    val isSelected = selectedType == investimento.tipo
                    TypeCard(
                        investimento = investimento,
                        isSelected = isSelected,
                        onTypeSelected = { typeName ->
                            // Define o tipo de investimento selecionado
                            selectedType = if (isSelected) null else typeName
                            // Filtra a lista completa de investimentos com base no tipo selecionado
                            filteredInvestments =
                                if (isSelected) allInvestments else allInvestments.filter { it.tipo == typeName }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(filteredInvestments) { investimento ->
                    InvestmentCard(investimento = investimento)
                }
            }
        }
    }


    @Composable
    fun InvestmentCard(investimento: Investment) {
        Card(modifier = Modifier.padding(bottom = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(3f)
                ) {
                    Text(
                        text = investimento.nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = investimento.tipo,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(
                    text = investimento.anoVencimento.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4B0082)
                )
            }
        }
    }

@Composable
fun TypeCard(investimento: Investment, onTypeSelected: (String) -> Unit, isSelected: Boolean) {
    OutlinedButton(
        onClick = {
            val typeName = investimento.tipo
            onTypeSelected(typeName)
        },
        modifier = Modifier
            .size(120.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = investimento.tipo.toUpperCase(),
            fontSize = 14.sp,
            modifier = Modifier.padding(4.dp)
        )
    }

    Spacer(modifier = Modifier.width(8.dp)) // Adiciona um espaçamento horizontal entre os botões
}




