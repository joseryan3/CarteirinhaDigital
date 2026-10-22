package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation.UnidadesCurricularesProfessorViewModel

class UnidadesCurricularesProfessorViewModelFactory(
    private val repository: ProfessorUnidadeCurricularRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadesCurricularesProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadesCurricularesProfessorViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
