package br.com.fiap.orionidea.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.orionidea.R

@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Olá Nome, comece a investir!",
                color = Color.Black,
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.helvetica_bold)),
            )
            Spacer(modifier = Modifier.height(7.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 200.dp, height = 50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Seu saldo atual",
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        fontSize = 23.sp,
                    )

                    Text(
                        text = "R$ 00,00",
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        fontSize = 23.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        navController.navigate("add_investment_screen")
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(189.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black), // Defina a espessura da borda e a cor da borda aqui
                    content = {
                        Text(
                            text = "CADASTRAR",
                            fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                            color = Color(0xFFFFA500),
                            fontSize = 21.sp,
                        )
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = {
                        navController.navigate("find_investment_screen")
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(189.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black), // Defina a espessura da borda e a cor da borda aqui
                    content = {
                        Text(
                            text = "EXTRATO",
                            fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                            color = Color(0xFF008000),
                            fontSize = 21.sp,
                        )
                    }
                )}
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(410.dp)
                    .padding(5.dp)
            ) {
                Text(
                    text = "Últimas notícias do mercado...",
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                    fontSize = 22.sp,
                )
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    //navController.navigate("find_investment_screen")
                },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Text(
                        text = "QUERO APRENDER A INVESTIR",
                        fontFamily = FontFamily(Font(R.font.helvetica_bold)),
                        fontSize = 20.sp,
                    )
                }
            )
        }
    }
}

