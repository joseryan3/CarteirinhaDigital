package com.senai.carteirinhadigital.feature.Login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.senai.carteirinhadigital.feature.Login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnUsuarioioChange -> {
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        errorMenssage = null
                    )
                }
            }

            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        errorMenssage = null
                    )
                }
            }

            LoginEvent.OnEntrarClick -> {
                fazerLogin()


            }

            LoginEvent.OnNavigacaoRealizada -> {
                _uiState.update {
                    it.copy(
                        usuarioLogado = null
                    )
                }
            }
        }
    }

    private fun fazerLogin() {
        val state = _uiState.value

        // Verifica se os campos estão vazios
        if (state.usuario.isBlank() || state.senha.isBlank()) {
            _uiState.update {
                it.copy(
                    errorMenssage = "Preencha login e senha"
                )
            }
            return
        }

        // Executa o login dentro de uma coroutine
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMenssage = null,
                    usuarioLogado = null
                )
            }

            val result = repository.login(
                state.usuario.trim(),
                state.senha.trim()
            )

            result
                .onSuccess { usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMenssage = null,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            errorMenssage = throwable.message?:"Erro ao Fazer login"
                        )
                    }
                }


            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}