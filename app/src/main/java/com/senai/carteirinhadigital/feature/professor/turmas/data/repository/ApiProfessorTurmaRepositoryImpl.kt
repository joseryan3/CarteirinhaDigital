package com.senai.carteirinhadigital.feature.professor.turmas.data.repository

import com.senai.carteirinhadigital.feature.professor.turmas.data.remote.service.ProfessorTurmaApi
import com.senai.carteirinhadigital.feature.professor.turmas.domain.model.TurmaProfessor
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import okio.IOException
import retrofit2.HttpException

class ApiProfessorTurmaRepositoryImpl(
    private val api: ProfessorTurmaApi
) : ProfessorTurmaRepository {

    override suspend fun listarTurmas(): Result<List<TurmaProfessor>> {
        return runCatching {
            api.listarTurmas().map { it.toDomain() }
        }.recoverCatching { throwable ->
            throw when (throwable) {
                is HttpException -> {
                    if (throwable.code() == 401) {
                        IllegalStateException("Sua sessão expirou. Faça login novamente.")
                    } else {
                        IllegalStateException("Erro ao carregar turmas (${throwable.code()}).")
                    }
                }
                is IOException -> IllegalStateException("Não foi possível conectar à API.")
                else -> IllegalStateException(throwable.message ?: "Erro ao carregar turmas.")
            }
        }
    }
}
