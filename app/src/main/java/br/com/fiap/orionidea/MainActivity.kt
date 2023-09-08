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
import br.com.fiap.orionidea.juros.SimulateScreenViewModel
import br.com.fiap.orionidea.screens.AddExpertScreen
import br.com.fiap.orionidea.screens.AddInvestmentScreen
import br.com.fiap.orionidea.screens.ContactExpertScreen
import br.com.fiap.orionidea.screens.FindInvestmentScreen
import br.com.fiap.orionidea.screens.GPTChatScreen
import br.com.fiap.orionidea.screens.LoginScreen
import br.com.fiap.orionidea.screens.MainScreen
import br.com.fiap.orionidea.screens.SimulateInvestmentScreen
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
                        startDestination = "add_expert_screen"
                    ) {
                        composable(route = "login_screen") {LoginScreen(navController)}
                        composable(route = "main_screen") {MainScreen(navController)}
                        composable(route = "simulate_investment_screen") { SimulateInvestmentScreen(
                            jurosScreenViewModel = SimulateScreenViewModel(),
                            navController = navController
                        )}
                        composable(route = "add_investment_screen") {AddInvestmentScreen(SimulateScreenViewModel(),navController)}
                        composable(route = "add_expert_screen") {AddExpertScreen(navController)}
                        composable(route = "contact_expert_screen") { ContactExpertScreen(navController) }
                        composable(route = "chat_screen") { GPTChatScreen(navController) }
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

