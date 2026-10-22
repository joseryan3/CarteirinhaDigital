package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.remote.dto

import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.model.UnidadeCurricularProfessor
import kotlinx.serialization.Serializable

@Serializable
data class UnidadeCurricularProfessorDto(
    val id: String,
    val nome: String,
    val turmaId: String? = null,
    val turma: String,
    val cargaHoraria: Int,
    val quantidadeAulas: Int,
    val aulasRealizadas: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
) {
    fun toDomain(): UnidadeCurricularProfessor {
        return UnidadeCurricularProfessor(
            id = id,
            nome = nome,
            turmaId = turmaId ?: "",
            turma = turma,
            cargaHoraria = cargaHoraria,
            quantidadeAulas = quantidadeAulas,
            aulasRealizadas = aulasRealizadas,
            diasSemana = diasSemana,
            horarioInicio = horarioInicio,
            horarioFim = horarioFim
        )
    }
}
