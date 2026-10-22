package com.senai.carteirinhadigital.feature.Login.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado
import com.senai.carteirinhadigital.feature.Login.presentation.LoginEvent
import com.senai.carteirinhadigital.feature.Login.presentation.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSucesso: (UsuarioLogado)->Unit,
    viewModel: LoginViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.usuarioLogado) {
        uiState.usuarioLogado?.let{ usuario->
            viewModel.onEvent(LoginEvent.OnNavigacaoRealizada)
            onLoginSucesso(usuario)
        }
    }

    LoginContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = modifier.fillMaxSize()
    )
}