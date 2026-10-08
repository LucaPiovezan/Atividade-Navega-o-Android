package com.luca.expedicao.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luca.expedicao.model.Trilha

/** Estado compartilhado por todas as telas do aplicativo. */
class ExpedicaoViewModel : ViewModel() {
    var estaLogado by mutableStateOf(false)
        private set

    private val trilhasIniciais = listOf(
        Trilha(1, "Pico da Neblina", "Serra da Mantiqueira, MG", "⛰️", "Desafiadora", 8.4, 1120, 6.0,
            "Subida exigente com vista ampla da serra. Comece cedo, leve água e confira as condições do tempo."),
        Trilha(2, "Cachoeira Escondida", "Parque Nacional do Iguaçu, PR", "💧", "Fácil", 3.2, 150, 1.5,
            "Caminho agradável até uma cachoeira de 40 metros, ideal para uma primeira caminhada em família."),
        Trilha(3, "Vale dos Vinhedos", "Serra Gaúcha, RS", "🍇", "Moderada", 6.0, 420, 3.0,
            "Percurso entre parreirais e mirantes, com belas paisagens ao longo de todo o caminho."),
        Trilha(4, "Dunas do Ceará", "Parque das Dunas, CE", "🏜️", "Moderada", 5.5, 90, 2.5,
            "Caminhada sobre a areia em um cenário aberto. Leve água, protetor solar e proteção para o sol."),
        Trilha(5, "Bosque das Araucárias", "São Paulo, SP", "🌲", "Fácil", 2.4, 60, 1.0,
            "Trilha curta entre araucárias, com pontos tranquilos para observar a natureza e descansar."),
        Trilha(6, "Cume do Itatiaia", "Parque Nacional do Itatiaia, RJ", "🧗", "Desafiadora", 11.0, 1450, 8.0,
            "Uma caminhada de alta montanha que exige preparo, planejamento e atenção à previsão do tempo.")
    )

    val trilhas = mutableStateListOf<Trilha>().apply { addAll(trilhasIniciais) }

    fun fazerLogin() { estaLogado = true }
    fun fazerLogout() { estaLogado = false }
    fun buscarTrilha(trilhaId: Int): Trilha? = trilhas.find { it.id == trilhaId }

    fun alternarConquista(trilhaId: Int) {
        val index = trilhas.indexOfFirst { it.id == trilhaId }
        if (index >= 0) trilhas[index] = trilhas[index].copy(conquistada = !trilhas[index].conquistada)
    }

    fun removerTrilha(trilhaId: Int) { trilhas.removeAll { it.id == trilhaId } }

    fun restaurarTrilhas() {
        trilhas.clear()
        trilhas.addAll(trilhasIniciais)
    }

    val totalTrilhas: Int get() = trilhas.size
    val trilhasConquistadas: Int get() = trilhas.count { it.conquistada }
    val progressoGeral: Float get() = if (totalTrilhas == 0) 0f else trilhasConquistadas.toFloat() / totalTrilhas
    val distanciaTotalConquistada: Double get() = trilhas.filter { it.conquistada }.sumOf { it.distanciaKm }
}
