package br.com.fiap.orionidea.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.orionidea.R
import br.com.fiap.orionidea.repository.InvestimentRepository

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val investimentRepository = InvestimentRepository(context)
    val sumValue = investimentRepository.sumValueApplied()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(2f, 1f)
                .graphicsLayer(rotationX = 180f)
                .offset(y = (-125).dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Olá, comece a investir!",
                color = Color(0xFF4938C3),
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

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF363EBC), Color(0xFF901EDD)
                                )
                            ), shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Button(onClick = {
                        navController.navigate("add_investment_screen")
                    },
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent, // Set the button background color to transparent
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        content = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(id = R.string.register),
                                    fontFamily = FontFamily(Font(R.font.free_sans)),
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                        })
                }

                Spacer(modifier = Modifier.width(15.dp))
                Spacer(modifier = Modifier.height(60.dp))

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF363EBC), Color(0xFF901EDD)
                                )
                            ), shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Button(onClick = {
                        navController.navigate("find_investment_screen")
                    },
                        modifier = Modifier.fillMaxSize(), // Changed from fillMaxWidth() to fillMaxSize() for full Box coverage
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        content = {
                            Text(
                                text = stringResource(id = R.string.extract),
                                fontFamily = FontFamily(Font(R.font.free_sans)),
                                color = Color.White,
                                fontSize = 20.sp,
                            )
                        })
                }
            }

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF901EDD), Color(0xFF363EBC)
                            )
                        ), shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Button(onClick = {
                    Toast.makeText(context, "Em desenvolvimento", Toast.LENGTH_SHORT).show()
                },
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.hot_topics),
                                fontFamily = FontFamily(Font(R.font.free_sans)),
                                color = Color.White,
                                fontSize = 20.sp,
                            )
                        }
                    })
            }

            Spacer(modifier = Modifier.height(85.dp))

            Button(onClick = {
                Toast.makeText(context, "Em desenvolvimento", Toast.LENGTH_SHORT).show()
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.simulate_investment),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF1F1F20),
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF1F1F20)
                        )
                    }
                })

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = {
                navController.navigate("chat_screen")
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.questions_bot),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF1F1F20),
                            fontSize = 19.sp,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color(0xFF1F1F20)
                        )
                    }
                })

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = {
                Toast.makeText(context, "Em desenvolvimento", Toast.LENGTH_SHORT).show()
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.contact_expert),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF1F1F20),
                            fontSize = 19.sp,
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null,
                            tint = Color(0xFF1F1F20)
                        )
                    }
                })

            Spacer(modifier = Modifier.height(95.dp))

            Button(onClick = {
                Toast.makeText(context, "Em desenvolvimento", Toast.LENGTH_SHORT).show()
            },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.want_to_be_expert),
                            fontFamily = FontFamily(Font(R.font.free_sans)),
                            color = Color(0xFF1F1F20),
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFF1F1F20)
                        )
                    }
                })
        }
    }
}