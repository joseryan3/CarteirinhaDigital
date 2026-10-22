package com.senai.carteirinhadigital.feature.professor.turmas.domain.repository

import com.senai.carteirinhadigital.feature.professor.turmas.domain.model.TurmaProfessor

interface ProfessorTurmaRepository {
    suspend fun listarTurmas(): Result<List<TurmaProfessor>>
}
