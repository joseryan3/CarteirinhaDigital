package com.senai.carteirinhadigital.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhadigital.R
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppShell(
    title: String,
    usuarioLogado: UsuarioLogado,
    canNavigateBack: Boolean,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun abrirDrawer() {
        scope.launch { drawerState.open() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFF1E0A38)
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    // Fundo com gradiente roxo no menu lateral / perfil
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF2E1A47),
                                        Color(0xFF1A0B2E),
                                        Color(0xFF0F041C)
                                    )
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 24.dp, vertical = 28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        // Avatar do Usuário
                        Image(
                            painter = painterResource(id = R.drawable.images),
                            contentDescription = "Avatar do usuário",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(96.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 3.dp,
                                    color = Color(0xFF00E5FF),
                                    shape = CircleShape
                                )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = usuarioLogado.nome,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Text(
                            text = "Matrícula ${usuarioLogado.matricula}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        HorizontalDivider(color = Color.White.copy(alpha = 0.2f))

                        // Alternar Tema Escuro
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Tema escuro",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Switch(
                                checked = darkTheme,
                                onCheckedChange = onDarkThemeChange,
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF00E5FF),
                                    uncheckedThumbColor = Color.Gray,
                                    uncheckedTrackColor = Color.DarkGray
                                )
                            )
                        }

                        HorizontalDivider(color = Color.White.copy(alpha = 0.2f))

                        Spacer(modifier = Modifier.weight(1f))

                        // Botão Sair estilizado
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp),
                            border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF00E5FF)),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFF00E5FF)
                            ),
                            onClick = onLogout
                        ) {
                            Text(
                                text = "Sair",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            containerColor = Color(0xFF1A0B2E), // Garante que a tela de fundo nunca fique branca ao abrir o drawer
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF1A0B2E),
                        navigationIconContentColor = Color(0xFF00E5FF),
                        titleContentColor = Color.White,
                        actionIconContentColor = Color(0xFF00E5FF)
                    ),
                    navigationIcon = {
                        if (canNavigateBack) {
                            IconButton(
                                onClick = onNavigateBack
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { abrirDrawer() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Abrir menu"
                                )
                            }
                        }
                    },
                    actions = {
                        if (canNavigateBack) {
                            IconButton(
                                onClick = { abrirDrawer() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Abrir menu"
                                )
                            }
                        }
                    }
                )
            },
            content = content
        )
    }
}
