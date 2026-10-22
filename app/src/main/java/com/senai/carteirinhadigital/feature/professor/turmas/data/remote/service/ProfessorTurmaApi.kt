package com.senai.carteirinhadigital.feature.professor.turmas.data.remote.service

import com.senai.carteirinhadigital.feature.professor.turmas.data.remote.dto.TurmaProfessorDto
import retrofit2.http.GET

interface ProfessorTurmaApi {
    @GET("professores/me/turmas")
    suspend fun listarTurmas(): List<TurmaProfessorDto>
}
