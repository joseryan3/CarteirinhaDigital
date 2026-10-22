package com.senai.carteirinhadigital.feature.aluno.carterinha.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhadigital.QrCode
import com.senai.carteirinhadigital.R
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado

@Composable
fun CarterinhaScreen(
    usuarioLogado: UsuarioLogado,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}) {

    Box(modifier = Modifier.fillMaxSize()) {

        // Fundo Crash Bandicoot
        Image(
            painter = painterResource(id = R.drawable.crashbandicootfundo),
            contentDescription = "Fundo Crash Bandicoot",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay escuro suave para melhorar a legibilidade
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(24.dp)
    ) {

        // Logo maior e mais destacada
        Image(
            painter = painterResource(R.drawable.senai),
            contentDescription = "Logo Senai",
            modifier = Modifier
                .size(120.dp)
        )

        // Foto do usuário com borda e sombra discretas
        Image(
            painter = painterResource(id = R.drawable.images),
            contentDescription = "Foto do usuário",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(5.dp, Color.Green, RoundedCornerShape(20.dp))
                .shadow(8.dp, RoundedCornerShape(20.dp))
        )

        // Nome estilizado
        Text(
            text = "José Ryan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Color(0xFF000000),
                    offset = Offset(2f, 2f),
                    blurRadius = 8f
                )
            )
        )

        // Curso estilizado
        Text(
            text = "Desenvolvimento de Sistemas",
            fontSize = 18.sp,
            color = Color(0xFFCCCCCC),
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                shadow = Shadow(
                    color = Color(0xFF000000),
                    offset = Offset(1f, 1f),
                    blurRadius = 4f
                )
            )
        )

        // Divider com borda suave
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.fillMaxWidth())

        // QR Code estilizado
        QrCode(
            "90000000001756464093",
            modifier = Modifier
                .size(160.dp)
                .border(4.dp, Color.Black, RoundedCornerShape(12.dp))
                .padding(10.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp))
        )
    }
}

