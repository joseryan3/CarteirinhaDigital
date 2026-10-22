package com.senai.carteirinhadigital.feature.Login.data.repository

import com.senai.carteirinhadigital.feature.Login.data.remote.dto.ErrorResponseDto
import com.senai.carteirinhadigital.feature.Login.data.remote.dto.LoginRequestDto
import com.senai.carteirinhadigital.feature.Login.data.remote.service.AuthApi
import com.senai.carteirinhadigital.feature.Login.domain.model.PerfilAcesso
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado
import com.senai.carteirinhadigital.feature.Login.domain.repository.LoginRepository
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException

class ApiLoginRepositoryImpl(
    private val api: AuthApi
) : LoginRepository {

    override suspend fun login(
        login: String,
        senha: String
    ): Result<UsuarioLogado> {

        return runCatching {

            val response =
                api.login(
                    LoginRequestDto(
                        login = login,
                        senha = senha
                    )
                )

            UsuarioLogado(
                id = response.id,
                nome = response.nome,
                matricula = response.matricula,
                perfil =
                    PerfilAcesso.fromApi(
                        response.perfil
                    ),
                curso = response.curso,
                turma = response.turma,
                token = response.token

            )

        }.recoverCatching { throwable ->

            throw mapToDomainError(
                throwable
            )
        }
    }

    private fun mapToDomainError(
        throwable: Throwable
    ): Throwable {

        return when (throwable) {

            is HttpException ->
                mapHttpException(throwable)

            is IOException ->
                IllegalStateException(
                    "Não foi possível conectar à API local. " +
                            "Verifique se ela está rodando."
                )

            else ->
                IllegalStateException(
                    throwable.message
                        ?: "Erro ao fazer login."
                )
        }
    }

    private fun mapHttpException(
        exception: HttpException
    ): Throwable {

        if (exception.code() == 401) {

            return IllegalArgumentException(
                "Login ou senha inválidos"
            )
        }

        val messageFromBody =
            exception
                .response()
                ?.errorBody()
                ?.string()
                ?.let { body ->

                    runCatching {

                        errorJson
                            .decodeFromString<ErrorResponseDto>(
                                body
                            )
                            .message

                    }.getOrNull()
                }

        return IllegalStateException(
            messageFromBody
                ?: "Erro no servidor (${exception.code()})."
        )
    }

    companion object {

        private val errorJson =
            Json {
                ignoreUnknownKeys = true
            }
    }
}