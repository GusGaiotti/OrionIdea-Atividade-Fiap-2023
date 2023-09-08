package br.com.fiap.orionidea.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.orionidea.R
import br.com.fiap.orionidea.repository.InvestimentoRepository


@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val investimentRepository = InvestimentoRepository(context)
    val sumValue = investimentRepository.sumValueApplied()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Olá nome, comece a investir!",
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
                        text = stringResource(id = R.string.your_balance),
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        fontSize = 23.sp,
                    )

                    Text(
                        text = "R$ $sumValue",
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        fontSize = 23.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(onClick = {
                    navController.navigate("add_investment_screen")
                },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    content = {
                        Text(
                            text = stringResource(id = R.string.register_investment),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF1B4CC5),
                            fontSize = 22.sp,
                        )
                    })

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedButton(onClick = {
                    navController.navigate("find_investment_screen")
                },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    content = {
                        Text(
                            text = stringResource(id = R.string.extract),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF008000),
                            fontSize = 22.sp,
                        )
                    })
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedButton(onClick = {
                navController.navigate("chat_screen")
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Text(
                        text = stringResource(id = R.string.questions_bot),
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        fontSize = 20.sp,
                    )
                })

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedButton(onClick = {
                navController.navigate("contact_expert_screen")
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Text(
                        text = stringResource(id = R.string.contact_expert),
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        color = Color(0xFF8B0000),
                        fontSize = 22.sp,
                    )
                })

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedButton(onClick = {
                navController.navigate("add_expert_screen")
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Text(
                        text = stringResource(id = R.string.want_to_be_expert),
                        fontFamily = FontFamily(Font(R.font.free_sans)),
                        color = Color(0xFFCF461A),
                        fontSize = 19.sp,
                    )
                })
        }
    }
}