package br.com.fiap.orionidea.screens

import OpenIAService
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import okhttp3.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GPTChatScreen() {

    val openIAService = OpenIAService()

    val scope = rememberCoroutineScope()
    var question by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.talk_to_orionbot),
            fontSize = 24.sp,
            color = Color(0xFFF186A4),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (isLoading) {
            Text(
                text = stringResource(id = R.string.searching),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        if (response.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.orionbot_message),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        fontSize = 16.sp
                    )
                    Text(
                        text = response, modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFF319131), shape = RoundedCornerShape(16.dp)
            ), contentAlignment = Alignment.Center, content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.tip),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        })

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = question, onValueChange = {
                    question = it
                }, keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send, keyboardType = KeyboardType.Text
                ), keyboardActions = KeyboardActions(onSend = {
                    scope.launch {
                        isLoading = true
                        openIAService.getResponse(question) { result ->
                            response = result
                            question = ""
                            isLoading = false
                        }
                    }
                }), modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        openIAService.getResponse(question) { result ->
                            response = result
                            question = ""
                            isLoading = false
                        }
                    }
                }, modifier = Modifier.height(IntrinsicSize.Max)
            ) {
                Text(text = stringResource(id = R.string.send))
            }
        }
    }
}

