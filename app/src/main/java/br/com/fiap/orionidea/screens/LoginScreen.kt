package br.com.fiap.orionidea.screens

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(2f, 1f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = stringResource(id = R.string.login),
                fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                fontSize = 32.sp,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.subtitle),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.free_sans)),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(25.dp))


            TextField(
                value = name,
                onValueChange = {
                    name = it
                    if (name.isNotEmpty()) nameError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),

                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.name),
                        textAlign = TextAlign.Start,
                        color = Color(0xFF3B3DBF),
                        fontSize = 17.sp
                    )
                },
                isError = nameError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            ErrorText(nameError, "Nome é obrigatório!")


            TextField(
                value = email,
                onValueChange = {
                    email = it
                    if (email.isNotEmpty()) emailError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),

                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.email),
                        textAlign = TextAlign.Start,
                        color = Color(0xFF3B3DBF),
                        fontSize = 17.sp
                    )
                },
            )


            ErrorText(emailError, "E-mail é obrigatório!")


            TextField(
                value = password,
                onValueChange = {
                    password = it
                    if (password.isNotEmpty()) passwordError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),

                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        textAlign = TextAlign.Start,
                        color = Color(0xFF3B3DBF),
                        fontSize = 17.sp
                    )
                },
                isError = passwordError,
                visualTransformation = PasswordVisualTransformation(),
            )

            ErrorText(passwordError, "A senha deve conter mais de 8 dígitos!")


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
                    .height(170.dp)
                    .padding(top = 120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(android.graphics.Color.parseColor("#3b3dc0"))
                )
            ) {
                Text(
                    text = stringResource(id = R.string.enter),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
