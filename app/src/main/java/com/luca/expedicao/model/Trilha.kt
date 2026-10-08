package com.luca.expedicao.model

data class Trilha(
    val id: Int,
    val nome: String,
    val local: String,
    val emoji: String,
    val dificuldade: String,
    val distanciaKm: Double,
    val desnivelM: Int,
    val duracaoHoras: Double,
    val descricao: String,
    val conquistada: Boolean = false
)
