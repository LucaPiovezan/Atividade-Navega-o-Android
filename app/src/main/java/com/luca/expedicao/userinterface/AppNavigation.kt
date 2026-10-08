package com.luca.expedicao.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.luca.expedicao.model.Rota
import com.luca.expedicao.viewmodel.ExpedicaoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Esta é a única criação do ViewModel. A mesma instância é compartilhada por todas as telas.
    val viewModel: ExpedicaoViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rota.Splash) {
        composable<Rota.Splash> {
            SplashScreen(viewModel = viewModel) {
                // A Splash só sinaliza que terminou; a decisão pertence ao host da navegação.
                val destino = if (viewModel.estaLogado) Rota.Trilhas else Rota.Login
                navController.navigate(destino) {
                    popUpTo<Rota.Splash> { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable<Rota.Login> {
            LoginScreen(viewModel = viewModel) {
                navController.navigate(Rota.Trilhas) {
                    popUpTo<Rota.Login> { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable<Rota.Trilhas> { PainelPrincipal(navController, viewModel, AbaPrincipal.TRILHAS) }
        composable<Rota.Progresso> { PainelPrincipal(navController, viewModel, AbaPrincipal.PROGRESSO) }
        composable<Rota.Perfil> { PainelPrincipal(navController, viewModel, AbaPrincipal.PERFIL) }

        composable<Rota.DetalheTrilha> { backStackEntry ->
            val rota = backStackEntry.toRoute<Rota.DetalheTrilha>()
            DetalheTrilhaScreen(
                trilhaId = rota.trilhaId,
                viewModel = viewModel,
                aoVoltar = { navController.popBackStack() }
            )
        }
    }
}
