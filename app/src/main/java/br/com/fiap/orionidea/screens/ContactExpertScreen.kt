package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import br.com.fiap.orionidea.components.TypeExpertCard
import br.com.fiap.orionidea.model.Expert
import br.com.fiap.orionidea.repository.ExpertRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactExpertScreen(navController: NavHostController) {

    val context = LocalContext.current
    val expertRepository = ExpertRepository(context)

    var searchTextState by remember {
        mutableStateOf("")
    }

    val allExperts by remember {
        mutableStateOf(expertRepository.listExperts())
    }

    var filteredExperts by remember {
        mutableStateOf(allExperts)
    }

    var selectedType by remember {
        mutableStateOf<String?>(null)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Especialistas",
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
                    filteredExperts = allExperts.filter { expert ->
                        expert.name.contains(searchTextState, ignoreCase = true)
                    }
                },
                modifier = Modifier
                    .weight(1f),
                label = {
                    Text(text = "Buscar Especialista")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        filteredExperts = allExperts.filter { expert ->
                            expert.name.contains(searchTextState, ignoreCase = true)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(modifier = Modifier.padding(16.dp)) {
            items(filteredExperts.distinctBy { it.name}) { expert ->
                val isSelected = selectedType == expert.name
                TypeExpertCard(
                    expert = expert
                ) { typeName ->
                    selectedType = if (isSelected) null else typeName
                    filteredExperts =
                        if (isSelected) allExperts else allExperts.filter { it.name == typeName }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(filteredExperts) { expert ->
                ExpertCard(expert = expert)
            }
        }
    }
}


@Composable
fun ExpertCard(expert: Expert) {
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
                    text = expert.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = expert.city,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = expert.telephone.toString(),
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




