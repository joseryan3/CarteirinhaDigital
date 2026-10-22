package com.senai.carteirinhadigital.feature.professor.turmas.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.senai.carteirinhadigital.feature.professor.turmas.domain.model.TurmaProfessor

@Composable
fun TurmaCard(
    turma: TurmaProfessor,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = turma.codigo,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Semestre: ${turma.semestre}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = turma.curso,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Quantidade de Alunos: ${turma.quantidadeAlunos}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Dias: ${turma.diasSemana.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Horário: ${turma.horarioInicio} - ${turma.horarioFim}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
