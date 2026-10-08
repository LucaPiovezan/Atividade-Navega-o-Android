package com.luca.expedicao.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luca.expedicao.viewmodel.ExpedicaoViewModel

@Composable
fun DetalheTrilhaScreen(
    trilhaId: Int,
    viewModel: ExpedicaoViewModel,
    aoVoltar: () -> Unit
) {
    val trilha = viewModel.buscarTrilha(trilhaId)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = aoVoltar) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar para a lista de trilhas")
            }
            Text("Detalhe da trilha", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }

        if (trilha == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Trilha não encontrada.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            return@Column
        }

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp)) {
            Box(Modifier.size(72.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center) { Text(trilha.emoji, fontSize = 34.sp) }
            Spacer(Modifier.size(16.dp))
            Text(trilha.nome, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(trilha.local, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.size(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipTexto(trilha.dificuldade)
                if (trilha.conquistada) ChipTexto("Conquistada")
            }
            Spacer(Modifier.size(16.dp))
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    InfoColuna("Distância", "${"%.1f".format(trilha.distanciaKm)} km")
                    InfoColuna("Desnível", "${trilha.desnivelM} m")
                    InfoColuna("Duração", "${"%.1f".format(trilha.duracaoHoras)} h")
                }
            }
            Spacer(Modifier.size(16.dp))
            Text("Sobre", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.size(8.dp))
            Text(trilha.descricao, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.size(24.dp))
            Button(onClick = { viewModel.alternarConquista(trilhaId) }, modifier = Modifier.fillMaxWidth().size(50.dp)) {
                Text(if (trilha.conquistada) "Desmarcar conquista" else "Marcar como conquistada")
            }
            Spacer(Modifier.size(12.dp))
            OutlinedButton(onClick = aoVoltar, modifier = Modifier.fillMaxWidth().size(50.dp)) { Text("Voltar às trilhas") }
            Spacer(Modifier.size(24.dp))
        }
    }
}

@Composable
private fun ChipTexto(texto: String) {
    Text(texto, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier.clip(RoundedCornerShape(50)).background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 12.dp, vertical = 6.dp))
}

@Composable
private fun InfoColuna(rotulo: String, valor: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(valor, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(rotulo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
