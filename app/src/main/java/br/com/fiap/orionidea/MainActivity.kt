package br.com.fiap.orionidea


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.orionidea.juros.JurosScreenViewModel
import br.com.fiap.orionidea.screens.AddInvestmentScreen
import br.com.fiap.orionidea.screens.FindInvestmentScreen
import br.com.fiap.orionidea.screens.LoginScreen
import br.com.fiap.orionidea.screens.MainScreen
import br.com.fiap.orionidea.ui.theme.OrionIdeaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrionIdeaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "add_investment_screen"
                    ) {
                        composable(route = "login_screen") {LoginScreen(navController)}
                        composable(route = "main_screen") {MainScreen(navController)       }
                        composable(route = "add_investment_screen") {AddInvestmentScreen(JurosScreenViewModel(),navController)}
                        composable(route = "find_investment_screen") {FindInvestmentScreen(
                                navController
                            )
                        }
                    }

                }
            }
        }
    }
}

