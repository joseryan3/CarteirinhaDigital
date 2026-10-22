package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.remote.service

import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.remote.dto.UnidadeCurricularProfessorDto
import retrofit2.http.GET

interface ProfessorUnidadeCurricularApi {
    @GET("professores/me/unidades-curriculares")
    suspend fun listarUnidadesCurriculares(): List<UnidadeCurricularProfessorDto>
}
