package com.senai.carteirinhadigital.feature.Login.presentation

import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado


data class LoginUiState(
    val usuario: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val errorMenssage: String? = null,
    val usuarioLogado: UsuarioLogado? = null

){
    val loginRealizado: Boolean
        get() = usuarioLogado != null
}

