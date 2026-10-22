package com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation

import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.model.UnidadeCurricular


data class UnidadeCurricularUiState(
    val listaUnidadesCurriculares: List<com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.model.UnidadeCurricular> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}