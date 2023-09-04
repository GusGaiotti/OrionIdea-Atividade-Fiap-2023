package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.orionidea.R
import br.com.fiap.orionidea.components.ErrorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }
    val minPasswordLength = 8

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_round_icon),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
        )
        Text(
            text = "Login",
            fontSize = 32.sp,
            color = Color(android.graphics.Color.parseColor("#6650a4"))
        )
        Text(text = stringResource(id = R.string.subtitle))
        Spacer(modifier = Modifier.height(25.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        if (name.isNotEmpty()) nameError = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    label = { Text(text = "Nome") },
                    isError = nameError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )

                ErrorText(nameError, "Nome é obrigatório!")

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (email.isNotEmpty()) emailError = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    label = { Text(text = "Email") },
                    isError = emailError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )

                ErrorText(emailError, "E-mail é obrigatório!")

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (password.isNotEmpty()) passwordError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Senha") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = passwordError,
                    visualTransformation = PasswordVisualTransformation(),
                )

                ErrorText(passwordError, "A senha deve conter mais de 8 dígitos!")

            }
        }
        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = {
                if (email.isEmpty() || password.length <= minPasswordLength || name.isEmpty()) {
                    emailError = email.isEmpty()
                    passwordError = password.length <= minPasswordLength
                    nameError = name.isEmpty()
                } else {
                    emailError = false
                    passwordError = false
                    nameError = false
                    if (password.length >= minPasswordLength) {
                        navController.navigate("main_screen")
                    }
                }
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Color(android.graphics.Color.parseColor("#6650a4"))
            )
        ) {
            Text(
                text = "Entrar",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


