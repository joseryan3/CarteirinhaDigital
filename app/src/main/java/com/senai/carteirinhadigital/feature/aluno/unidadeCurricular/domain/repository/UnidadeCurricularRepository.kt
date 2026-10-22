package com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.repository

import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listarUnidadesCurriculares(): Result<List<com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.model.UnidadeCurricular>>
}