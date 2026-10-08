package com.luca.expedicao.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface Rota {
    @Serializable
    data object Splash : Rota

    @Serializable
    data object Login : Rota

    @Serializable
    data object Trilhas : Rota

    @Serializable
    data object Progresso : Rota

    @Serializable
    data object Perfil : Rota

    @Serializable
    data class DetalheTrilha(val trilhaId: Int) : Rota
}
