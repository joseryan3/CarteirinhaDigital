package com.senai.carteirinhadigital.feature.Login.domain.repository

import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login(login: String, senha: String): Result<UsuarioLogado>
}