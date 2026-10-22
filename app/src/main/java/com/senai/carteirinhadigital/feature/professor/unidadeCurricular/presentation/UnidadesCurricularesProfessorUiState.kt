package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation

import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.model.UnidadeCurricularProfessor

data class UnidadesCurricularesProfessorUiState(
    val listaUCs: List<UnidadeCurricularProfessor> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
