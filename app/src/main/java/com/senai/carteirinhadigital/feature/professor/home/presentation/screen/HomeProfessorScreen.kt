package com.senai.carteirinhadigital.feature.professor.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhadigital.core.designesystem.theme.component.NavigationButton
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado

@Composable
fun HomeProfessorScreen(
    usuarioLogado: UsuarioLogado,
    onTurmasClick: () -> Unit,
    onUnidadesCurricularesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Fundo com gradiente temático (Roxo / Galáxia / Ciano)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1A0B2E), Color(0xFF0A0512), Color(0xFF00363A))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SENAI • Professor",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00E5FF),
                letterSpacing = 1.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF006064).copy(alpha = 0.85f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Painel do Docente",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFB2DFDB)
                    )

                    Text(
                        text = usuarioLogado.nome,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Matrícula: ${usuarioLogado.matricula}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF00E5FF)
                    )

                    Text(
                        text = "Acesse suas turmas e unidades curriculares designadas.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            NavigationButton(
                text = "Minhas Turmas",
                onClick = onTurmasClick,
                modifier = Modifier.fillMaxWidth()
            )

            NavigationButton(
                text = "Unidades Curriculares",
                onClick = onUnidadesCurricularesClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
