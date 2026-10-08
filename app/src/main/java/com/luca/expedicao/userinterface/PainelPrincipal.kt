package com.luca.expedicao.userinterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.luca.expedicao.model.Rota
import com.luca.expedicao.viewmodel.ExpedicaoViewModel

enum class AbaPrincipal { TRILHAS, PROGRESSO, PERFIL }

@Composable
fun PainelPrincipal(
    navController: NavHostController,
    viewModel: ExpedicaoViewModel,
    abaAtual: AbaPrincipal
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.TRILHAS,
                    onClick = { navegarParaAba(navController, Rota.Trilhas) },
                    icon = { Icon(Icons.Filled.Terrain, contentDescription = "Trilhas") },
                    label = { Text("Trilhas") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PROGRESSO,
                    onClick = { navegarParaAba(navController, Rota.Progresso) },
                    icon = { Icon(Icons.Filled.BarChart, contentDescription = "Progresso") },
                    label = { Text("Progresso") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PERFIL,
                    onClick = { navegarParaAba(navController, Rota.Perfil) },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        when (abaAtual) {
            AbaPrincipal.TRILHAS -> ListaTrilhasScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoAbrirDetalhe = { id -> navController.navigate(Rota.DetalheTrilha(id)) }
            )
            AbaPrincipal.PROGRESSO -> ProgressoScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel
            )
            AbaPrincipal.PERFIL -> PerfilScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoSair = {
                    viewModel.fazerLogout()
                    navController.navigate(Rota.Login) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

internal fun navegarParaAba(navController: NavHostController, destino: Rota) {
    navController.navigate(destino) {
        // Trilhas é a raiz das três abas depois que a Splash conclui o redirecionamento.
        popUpTo<Rota.Trilhas> { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
