package com.luca.expedicao.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luca.expedicao.viewmodel.ExpedicaoViewModel

@Composable
fun ProgressoScreen(modifier: Modifier = Modifier, viewModel: ExpedicaoViewModel) {
    LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Progresso da expedição", style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold)
        }
        item {
            Card(shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${viewModel.trilhasConquistadas} de ${viewModel.totalTrilhas} trilhas conquistadas",
                        style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(16.dp))
                    LinearProgressIndicator(progress = { viewModel.progressoGeral },
                        modifier = Modifier.fillMaxWidth().height(10.dp), color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(16.dp))
                    Text("${"%.1f".format(viewModel.distanciaTotalConquistada)} km percorridos",
                        style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        if (viewModel.trilhas.isEmpty()) {
            item {
                Column(Modifier.fillMaxWidth().padding(top = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Sem dados por enquanto.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        } else {
            item { Text("Todas as trilhas", style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 8.dp)) }
            items(viewModel.trilhas, key = { it.id }) { trilha ->
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(Modifier.fillMaxWidth().padding(14.dp)) {
                        Text("${trilha.emoji} ${trilha.nome}", style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(progress = { if (trilha.conquistada) 1f else 0f },
                            modifier = Modifier.fillMaxWidth().height(6.dp))
                    }
                }
            }
        }
    }
}
