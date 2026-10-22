package com.senai.carteirinhadigital.feature.professor.turmas.presentation

import com.senai.carteirinhadigital.feature.professor.turmas.domain.model.TurmaProfessor

data class TurmasUiState(
    val listaTurmas: List<TurmaProfessor> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
