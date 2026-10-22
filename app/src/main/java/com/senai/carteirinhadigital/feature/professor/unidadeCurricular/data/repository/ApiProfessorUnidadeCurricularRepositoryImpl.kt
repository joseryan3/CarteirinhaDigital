package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.repository

import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.remote.service.ProfessorUnidadeCurricularApi
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.model.UnidadeCurricularProfessor
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository
import okio.IOException
import retrofit2.HttpException

class ApiProfessorUnidadeCurricularRepositoryImpl(
    private val api: ProfessorUnidadeCurricularApi
) : ProfessorUnidadeCurricularRepository {

    override suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>> {
        return runCatching {
            api.listarUnidadesCurriculares().map { it.toDomain() }
        }.recoverCatching { throwable ->
            throw when (throwable) {
                is HttpException -> {
                    if (throwable.code() == 401) {
                        IllegalStateException("Sua sessão expirou. Faça login novamente.")
                    } else {
                        IllegalStateException("Erro ao carregar UCs (${throwable.code()}).")
                    }
                }
                is IOException -> IllegalStateException("Não foi possível conectar à API.")
                else -> IllegalStateException(throwable.message ?: "Erro ao carregar unidades curriculares.")
            }
        }
    }
}
