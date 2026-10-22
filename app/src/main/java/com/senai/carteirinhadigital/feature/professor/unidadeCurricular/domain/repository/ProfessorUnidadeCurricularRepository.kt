package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository

import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.model.UnidadeCurricularProfessor

interface ProfessorUnidadeCurricularRepository {
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>>
}
