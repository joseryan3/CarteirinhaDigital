package com.senai.carteirinhadigital.core.designesystem.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.height(52.dp),
        onClick = onClick,
        shape = RoundedCornerShape(26.dp), // Cantos arredondados (estilo cápsula)
        border = BorderStroke(
            width = 2.dp,
            color = Color(0xFF00E5FF) // Borda Ciano Neon
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(alpha = 0.65f), // Fundo preto/escuro elegante
            contentColor = Color.White // Texto Branco
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
