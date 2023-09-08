package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.orionidea.components.ExpertForm
import br.com.fiap.orionidea.repository.ExpertRepository

@Composable
fun AddExpertScreen() {
    val context = LocalContext.current
    val expertRepository = ExpertRepository(context)

    var nameState by remember { mutableStateOf("") }
    var cityState by remember { mutableStateOf("") }
    var telephoneState by remember { mutableStateOf("") }

    var listExpertsState by remember {
        mutableStateOf(expertRepository.listExperts())
    }

    Column {
        ExpertForm(name = nameState,
            city = cityState,
            telephone = telephoneState,
            onNameChange = { nameState = it },
            onCityChange = { cityState = it },
            onTelephoneChange = { telephoneState = it }) {
            listExpertsState = expertRepository.listExperts()
        }
    }

}